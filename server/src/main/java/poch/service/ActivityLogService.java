package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.ActivityLog;
import poch.repository.ActivityLogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public List<ActivityLog> getByUser(Long userId) {
        return activityLogRepository.findByUserId(userId);
    }

    public ActivityLog save(ActivityLog log) {
        return activityLogRepository.save(log);
    }

    public void log(Long userId, String action, String details) {
        ActivityLog log = new ActivityLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now().toString());

        activityLogRepository.save(log);
    }
}
