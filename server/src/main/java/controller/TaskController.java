package controller;

import entity.Task;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

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
        return taskService.getTasksByGroup(id);
    }

    @GetMapping("/creator/{id}")
    public List<Task> getByCreator(@PathVariable Long id) {
        return taskService.getTasksByCreator(id);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getBy_id(id);
    }

    @PostMapping
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
