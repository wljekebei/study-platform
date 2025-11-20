package poch.service;
import poch.dto.GroupCreateDTO;
import poch.dto.GroupUpdateDTO;
import poch.dto.GroupResponseDTO;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import poch.entity.Group;
import poch.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getByCreator(Long creatorId) {
        return groupRepository.findByCreatedBy(creatorId);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
    public GroupResponseDTO create(GroupCreateDTO dto) {
        Group g = new Group();
        g.setName(dto.name);
        g.setDescription(dto.description);
        g.setCreatedBy(dto.createdBy);
        g.setCreatedAt(LocalDateTime.now());
        return map(groupRepository.save(g));
    }

    public GroupResponseDTO getById(Long id) {
        return groupRepository.findById(id)
                .map(this::map)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public List<GroupResponseDTO> getAll() {
        return groupRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public GroupResponseDTO update(Long id, GroupUpdateDTO dto) {
        Group g = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        g.setName(dto.name);
        g.setDescription(dto.description);

        return map(groupRepository.save(g));
    }

    private GroupResponseDTO map(Group g) {
        GroupResponseDTO dto = new GroupResponseDTO();
        dto.id = g.getGroupId();
        dto.name = g.getName();
        dto.description = g.getDescription();
        dto.createdBy = g.getCreatedBy();
        dto.createdAt = g.getCreatedAt() != null ? g.getCreatedAt().toString() : null;
        return dto;
    }
}
