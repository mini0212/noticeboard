package task.noticeboard.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.noticeboard.entity.Comment;
import task.noticeboard.entity.Post;
import task.noticeboard.repository.CommentRepository;
import task.noticeboard.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PostService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public PostService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	public Post createPost(Post post) {
		Post createdPost = Post.builder()
						.title(post.getTitle())
						.content(post.getContent())
						.build();
		return postRepository.save(createdPost);
	}

	public Post updatePost(Long postId, Post post) {
		if (post.getIsDeleted()) {
			throw new RuntimeException("이미 삭제된 게시글입니다.");
		}
		post.setTitle(post.getTitle());
		post.setContent(post.getContent());
		post.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}

	public void deletePost(Long postId) {
		Post post = postRepository.findById(postId)
						.orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
		if (post.getIsDeleted()) {
			throw new RuntimeException("이미 삭제된 게시글입니다.");
		}
		post.setIsDeleted(true);
		post.setUpdatedAt(LocalDateTime.now());
		// 댓글 삭제
		List<Comment> comments = commentRepository.findByPostIdAndIsDeletedFalse(postId);
		for (Comment comment : comments) {
			comment.setIsDeleted(true);
			commentRepository.save(comment);
		}
		postRepository.save(post);
	}

	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findByIsDeletedFalse(pageable);
	}

	public Post getPost(Long postId) {
		Post post = postRepository.findById(postId)
						.orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
		if (post.getIsDeleted()) {
			throw new RuntimeException("이미 삭제된 게시글입니다.");
		}
		return post;
	}
}
