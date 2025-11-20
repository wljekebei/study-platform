package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.Task;
import poch.entity.TaskStatus;
import poch.repository.TaskRepository;
import java.time.LocalDate;


import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getByGroup(Long groupId) {
        return taskRepository.findByGroupId(groupId);
    }

    public List<Task> getByCreator(Long creatorId) {
        return taskRepository.findByCreatedBy(creatorId);
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
    public Task updateTask(Long id, String title, String description, String deadline, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (title != null) task.setTitle(title);
        if (description != null) task.setDescription(description);
        if (deadline != null) task.setDeadline(deadline);
        if (status != null) task.setStatus(TaskStatus.valueOf(status));
        return taskRepository.save(task);
    }

    public List<Task> getUpcomingTasks(int days) {
        LocalDate limit = LocalDate.now().plusDays(days);
        return taskRepository.findByDeadlineLessThanEqual(limit.toString());
    }

    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        return taskRepository.findByDeadlineLessThan(today.toString());
    }

}
