package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.ActivityLog;
import poch.repository.ActivityLogRepository;

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

    public void delete(Long id) {
        activityLogRepository.deleteById(id);
    }
}
