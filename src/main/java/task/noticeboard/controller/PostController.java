package task.noticeboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.noticeboard.entity.Post;
import task.noticeboard.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
@Slf4j
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public String getPostList(Model model) {
		List<Post> posts = postService.getPostList();
		model.addAttribute("posts", posts);
		return "/posts";
	}

	@GetMapping("/add")
	public String addPost() {
		return "/addPost";
	}

	@GetMapping("/{postId}")
	public String getPost(@PathVariable Long postId, Model model) {
		Post post = postService.getPost(postId);
		model.addAttribute("post", post);
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
