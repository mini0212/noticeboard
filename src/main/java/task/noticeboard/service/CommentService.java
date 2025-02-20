package task.noticeboard.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import task.noticeboard.entity.Comment;
import task.noticeboard.entity.Post;
import task.noticeboard.repository.CommentRepository;
import task.noticeboard.repository.PostRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class CommentService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	public Comment creatComment(Long postId, String content) {
		Post post = postRepository.findById(postId)
						.orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
		if (post.getIsDeleted()) {
			throw new RuntimeException("삭제된 게시글에는 댓글을 작성할 수 없습니다.");
		}
		Comment comment = Comment.builder()
						.post(post)
						.content(content)
						.build();
		return commentRepository.save(comment);
	}

	public Comment updateComment(Long commentId, String content) {
		Comment comment = commentRepository.findById(commentId)
						.orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
		if (comment.getIsDeleted()) {
			throw new RuntimeException("삭제된 댓글은 수정할 수 없습니다.");
		}
		comment.setContent(content);
		comment.setUpdatedAt(LocalDateTime.now());
		return comment;
	}

	public void deleteComment(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
						.orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
		if (comment.getIsDeleted()) {
			throw new RuntimeException("이미 삭제된 댓글입니다.");
		}
		comment.setIsDeleted(true);
		comment.setUpdatedAt(LocalDateTime.now());
		commentRepository.save(comment);
	}
}
