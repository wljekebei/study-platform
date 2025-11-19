package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByGroupId(Long groupId);

    List<Task> findByCreatedBy(Long createdBy);
    List<Task> findByDeadlineLessThan(String deadline);

    List<Task> findByDeadlineLessThanEqual(String deadline);

    List<Task> findByDeadlineGreaterThanEqual(String deadline);

}
