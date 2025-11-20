package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.ActivityLog;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByUserId(Long userId);
}
