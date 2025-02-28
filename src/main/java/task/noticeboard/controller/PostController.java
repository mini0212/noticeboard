package task.noticeboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.noticeboard.entity.Comment;
import task.noticeboard.entity.Post;
import task.noticeboard.service.CommentService;
import task.noticeboard.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
@Slf4j
public class PostController {
	private final PostService postService;
	private final CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping
	public String getPostList(@RequestParam(defaultValue = "0") int page,
	                          @RequestParam(defaultValue = "5") int size,
	                          Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Post> posts = postService.getPostList(pageable);
		model.addAttribute("posts", posts);
		model.addAttribute("currentPage", posts.getNumber());
		model.addAttribute("totalPages", posts.getTotalPages());
		model.addAttribute("size", size);
		return "/posts";
	}

	@GetMapping("/add")
	public String addPost() {
		return "/addPost";
	}

	@GetMapping("/{postId}")
	public String getPost(@PathVariable Long postId, Model model) {
		Post post = postService.getPost(postId);
		List<Comment> comments = commentService.getCommentByPostId(postId);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "/post";
	}

	@PostMapping
	public String createPost(@ModelAttribute Post post) {
		postService.createPost(post);
		return "redirect:/posts";
	}

	@GetMapping("/{postId}/edit")
	public String updatePost(@PathVariable Long postId, Model model) {
		Post post = postService.getPost(postId);
		model.addAttribute("post", post);
		return "/editPost";
	}

	@PostMapping("/{postId}/edit")
	public String update(@PathVariable Long postId, @ModelAttribute Post post) {
		post.setId(postId);
		postService.updatePost(postId, post);
		return "redirect:/posts/{postId}";
	}

	@PostMapping("/{postId}")
	public String deletePost(@PathVariable Long postId) {
		log.info("삭제 요청" + postId);
		postService.deletePost(postId);
		return "redirect:/posts";
	}

}
