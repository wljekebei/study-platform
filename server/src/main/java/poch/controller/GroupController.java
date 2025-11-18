package poch.controller;
import poch.dto.GroupCreateDTO;
import poch.dto.GroupUpdateDTO;
import poch.dto.GroupResponseDTO;
import org.springframework.web.bind.annotation.*;
import poch.entity.Group;
import poch.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/creator/{id}")
    public List<Group> getByCreator(@PathVariable Long id) {
        return groupService.getByCreator(id);
    }

    @PostMapping
    public Group create(@RequestBody Group group) {
        return groupService.save(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
    @GetMapping
    public List<GroupResponseDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public GroupResponseDTO getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PostMapping("/create")
    public GroupResponseDTO create(@RequestBody GroupCreateDTO dto) {
        return groupService.create(dto);
    }

    @PutMapping("/{id}")
    public GroupResponseDTO update(@PathVariable Long id, @RequestBody GroupUpdateDTO dto) {
        return groupService.update(id, dto);
    }
}
