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
		//TODO: 존재하지 않는 경우 예외처리
		Post post = postRepository.findById(postId).orElseThrow();
		if (post.getIsDeleted()) {
			// TODO: 삭제된 게시글에 대한 예외처리
		}
		Comment comment = Comment.builder()
						.post(post)
						.content(content)
						.build();
		return commentRepository.save(comment);
	}

	public Comment updateComment(Long commentId, String content) {
		//TODO: 존재하지 않는 경우 예외처리
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		if (comment.getIsDeleted()) {
			// TODO: 삭제된 댓글에 대한 예외처리
		}
		comment.setContent(content);
		comment.setUpdatedAt(LocalDateTime.now());
		return comment;
	}

	public void deleteComment(Long commentId) {
		//TODO: 존재하지 않는 경우 예외처리
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		if (comment.getIsDeleted()) {
			// TODO: 삭제된 댓글에 대한 예외처리
		}
		comment.setIsDeleted(true);
		comment.setUpdatedAt(LocalDateTime.now());
		commentRepository.save(comment);
	}
}
