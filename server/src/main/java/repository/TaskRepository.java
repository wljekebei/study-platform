package repository;

import entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByGroup_id(Long groupId);

    List<Task> findByCreated_by(Long createdBy);
}
