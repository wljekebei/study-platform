package poch.service;

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
}
