package poch.service;

import org.springframework.stereotype.Service;
import poch.dto.UserStatsDTO;
import poch.entity.TaskStatus;
import poch.repository.MembershipRepository;
import poch.repository.ResourceRepository;
import poch.repository.TaskRepository;

import java.util.List;

@Service
public class UserStatsService {

    private final MembershipRepository membershipRepository;
    private final TaskRepository taskRepository;
    private final ResourceRepository resourceRepository;

    public UserStatsService(MembershipRepository membershipRepository,
                            TaskRepository taskRepository,
                            ResourceRepository resourceRepository) {
        this.membershipRepository = membershipRepository;
        this.taskRepository = taskRepository;
        this.resourceRepository = resourceRepository;
    }

    public UserStatsDTO getStats(Long userId) {

        UserStatsDTO dto = new UserStatsDTO();


        dto.groupsCount = membershipRepository.countByUserId(userId);


        List<Long> groupIds = membershipRepository.findGroupIdsByUserId(userId);


        dto.uploadedResources = resourceRepository.countByUploadedBy(userId);


        if (groupIds == null || groupIds.isEmpty()) {
            dto.totalTasks = 0L;
            dto.tasksDone = 0L;
            dto.tasksNotDone = 0L;
            return dto;
        }


        dto.totalTasks = taskRepository.countByGroupIdIn(groupIds);


        dto.tasksDone = taskRepository.countByGroupIdInAndStatus(groupIds, TaskStatus.DONE);

        dto.tasksNotDone = taskRepository.countByGroupIdInAndStatusNot(groupIds, TaskStatus.DONE);

        return dto;
    }
}
