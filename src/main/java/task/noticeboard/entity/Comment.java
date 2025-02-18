package task.noticeboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	private Boolean isDeleted = false;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
}
