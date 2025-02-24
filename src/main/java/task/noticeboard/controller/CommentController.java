package task.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import task.noticeboard.entity.Comment;
import task.noticeboard.service.CommentService;

@Controller
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public String createComment(@PathVariable Long postId,
	                            @RequestParam String content) {
		commentService.creatComment(postId, content);
		return "redirect:/posts/{postId}";
	}

	@PostMapping("/{commentId}/edit")
	public String updateComment(@PathVariable Long postId,
	                            @PathVariable Long commentId,
	                            @RequestParam String content) {
		commentService.updateComment(commentId, content);
		return "redirect:/posts/{postId}";
	}

	@PostMapping("/{commentId}/delete")
public String deleteComment(@PathVariable Long postId,
	                          @PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/posts/{postId}";
	}
}
