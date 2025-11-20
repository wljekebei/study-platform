package poch.controller;

import org.springframework.web.bind.annotation.*;
import poch.entity.ActivityLog;
import poch.service.ActivityLogService;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping("/user/{id}")
    public List<ActivityLog> getByUser(@PathVariable Long id) {
        return activityLogService.getByUser(id);
    }

    @PostMapping
    public ActivityLog create(@RequestBody ActivityLog log) {
        return activityLogService.save(log);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        activityLogService.delete(id);
    }
}
