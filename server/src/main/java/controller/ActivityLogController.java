package controller;

import entity.ActivityLog;
import org.springframework.web.bind.annotation.*;
import service.ActivityLogService;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping("/user/{id}")
    public List<ActivityLog> getByUser(@PathVariable Long id) {
        return activityLogService.getLogsByUser(id);
    }

    @PostMapping
    public ActivityLog create(@RequestBody ActivityLog log) {
        return activityLogService.save(log);
    }
}
