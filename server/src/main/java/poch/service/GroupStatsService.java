package poch.service;

import org.springframework.stereotype.Service;
import poch.dto.GroupStatsDTO;
import poch.entity.TaskStatus;
import poch.repository.MembershipRepository;
import poch.repository.TaskRepository;
import poch.repository.ResourceRepository;

@Service
public class GroupStatsService {

    private final MembershipRepository membershipRepository;
    private final TaskRepository taskRepository;
    private final ResourceRepository resourceRepository;

    public GroupStatsService(
            MembershipRepository membershipRepository,
            TaskRepository taskRepository,
            ResourceRepository resourceRepository
    ) {
        this.membershipRepository = membershipRepository;
        this.taskRepository = taskRepository;
        this.resourceRepository = resourceRepository;
    }

    public GroupStatsDTO getStats(Long groupId) {

        GroupStatsDTO dto = new GroupStatsDTO();

        dto.members = membershipRepository.countByGroupId(groupId);
        dto.tasks = taskRepository.countByGroupId(groupId);
        dto.links = resourceRepository.countByGroupIdAndType(groupId, "LINK");
        dto.files = resourceRepository.countByGroupIdAndType(groupId, "FILE");


        dto.done = taskRepository.countByGroupIdAndStatus(groupId, TaskStatus.DONE);
        dto.inProgress = taskRepository.countByGroupIdAndStatus(groupId, TaskStatus.IN_PROGRESS);
        dto.open = taskRepository.countByGroupIdAndStatus(groupId, TaskStatus.OPEN);

        return dto;
    }
}
