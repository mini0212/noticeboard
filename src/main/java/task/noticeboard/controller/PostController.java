package task.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.noticeboard.entity.Post;
import task.noticeboard.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
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

	@GetMapping("/{postId}")
	public String getPost(@PathVariable Long postId, Model model) {
		Post post = postService.getPost(postId);
		model.addAttribute("model", model);
		return "/post";
	}

	@PostMapping
	public String createPost(@RequestBody Post request) {
		postService.createPost(request.getTitle(), request.getContent());
		return "redirect:/posts/{postId}";
	}

	@PostMapping("/{postId}/edit")
	public String updatePost(@PathVariable Long postId, @ModelAttribute Post post) {
		postService.updatePost(postId, post);
		return "redirect:/posts/{postId}";
	}

	@DeleteMapping("/{postId}")
	public String deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
		return "redirect:/posts";
	}

}
