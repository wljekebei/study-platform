package service;

import entity.Group;
import org.springframework.stereotype.Service;
import repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Group> getByOwner(Long createdBy) {
        return groupRepository.findByCreated_by(createdBy);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
