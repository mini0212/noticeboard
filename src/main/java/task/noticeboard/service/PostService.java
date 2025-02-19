package task.noticeboard.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import task.noticeboard.entity.Post;
import task.noticeboard.repository.CommentRepository;
import task.noticeboard.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public PostService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	public Post createPost(String title, String content) {
		Post post = Post.builder()
						.title(title)
						.content(content)
						.build();
		return postRepository.save(post);
	}

	public Post updatePost(Long postId, String title, String content) {
		//TODO: 존재하지 않는 경우 예외처리
		Post post = postRepository.findById(postId).orElseThrow();
		if (post.getIsDeleted()) {
			// TODO: 삭제된 게시글에 대한 예외처리
		}
		post.setTitle(title);
		post.setContent(content);
		post.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}

	public void deletePost(Long postId) {
		//TODO: 존재하지 않는 경우 예외처리
		Post post = postRepository.findById(postId).orElseThrow();
		if (post.getIsDeleted()) {
			// TODO: 삭제된 게시글에 대한 예외처리
		}
		post.setIsDeleted(true);
		post.setUpdatedAt(LocalDateTime.now());
		// TODO: 댓글 삭제
		postRepository.save(post);
	}

	public List<Post> getPostList() {
		return postRepository.findAll();
	}

	public Post getPost(Long postId) {
		//TODO: 존재하지 않는 경우 예외처리
		Post post = postRepository.findById(postId).orElseThrow();
		if (post.getIsDeleted()) {
			// TODO: 삭제된 게시글에 대한 예외처리
		}
		return post;
	}
}
