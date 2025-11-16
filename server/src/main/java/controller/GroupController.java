package controller;

import entity.Group;
import org.springframework.web.bind.annotation.*;
import service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @GetMapping("/owner/{id}")
    public List<Group> getByOwner(@PathVariable Long id) {
        return groupService.getByOwner(id);
    }

    @PostMapping
    public Group create(@RequestBody Group group) {
        return groupService.save(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}
