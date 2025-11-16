package service;

import entity.ActivityLog;
import org.springframework.stereotype.Service;
import repository.ActivityLogRepository;

import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public List<ActivityLog> getLogsByUser(Long userId) {
        return activityLogRepository.findByUser_id(userId);
    }

    public ActivityLog save(ActivityLog log) {
        return activityLogRepository.save(log);
    }
}
