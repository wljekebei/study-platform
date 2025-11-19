package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.Task;
import poch.entity.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByGroupId(Long groupId);

    List<Task> findByCreatedBy(Long createdBy);

    List<Task> findByDeadlineLessThan(String deadline);

    List<Task> findByDeadlineLessThanEqual(String deadline);

    List<Task> findByDeadlineGreaterThanEqual(String deadline);

    long countByGroupId(Long groupId);

    long countByGroupIdIn(List<Long> groupIds);


    long countByGroupIdInAndStatus(List<Long> groupIds, TaskStatus status);

    long countByGroupIdInAndStatusNot(List<Long> groupIds, TaskStatus status);

    long countByGroupIdAndStatus(Long groupId, TaskStatus status);
}
