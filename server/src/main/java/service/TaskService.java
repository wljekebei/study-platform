package service;

import entity.Task;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByGroup(Long groupId) {
        return taskRepository.findByGroup_id(groupId);
    }

    public List<Task> getTasksByCreator(Long creatorId) {
        return taskRepository.findByCreated_by(creatorId);
    }

    public Task getBy_id(Long task_id) {
        return taskRepository.findById(task_id).orElse(null);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
