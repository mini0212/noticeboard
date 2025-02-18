package task.noticeboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	private Boolean isDeleted = false;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
}
