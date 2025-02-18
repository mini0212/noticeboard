package task.noticeboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.noticeboard.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
