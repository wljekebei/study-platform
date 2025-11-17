package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.Task;
import poch.repository.TaskRepository;

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
}
