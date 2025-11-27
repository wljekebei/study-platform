package poch.controller;

import org.springframework.web.bind.annotation.*;
import poch.dto.UpdateTaskDTO;
import poch.entity.Task;
import poch.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/group/{id}")
    public List<Task> getByGroup(@PathVariable Long id) {
        return taskService.getByGroup(id);
    }

    @GetMapping("/creator/{id}")
    public List<Task> getByCreator(@PathVariable Long id) {
        return taskService.getByCreator(id);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody UpdateTaskDTO dto) {
        return taskService.updateTask(
                id,
                dto.title,
                dto.description,
                dto.deadline,
                dto.status
        );
    }

    @GetMapping("/upcoming")
    public List<Task> getUpcoming(@RequestParam int days) {
        return taskService.getUpcomingTasks(days);
    }

    @GetMapping("/overdue")
    public List<Task> getOverdue() {
        return taskService.getOverdueTasks();
    }
}