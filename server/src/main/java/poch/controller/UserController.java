package poch.controller;

import poch.dto.UserResponseDTO;
import poch.dto.UserUpdateDTO;
import poch.entity.Group;
import poch.entity.User;
import org.springframework.web.bind.annotation.*;
import poch.service.UserService;
import poch.service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MembershipService membershipService;

    public UserController(UserService userService,
                          MembershipService membershipService) {
        this.userService = userService;
        this.membershipService = membershipService;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/{id}/groups")
    public List<Group> getUserGroups(@PathVariable Long id) {
        return membershipService.getGroupsOfUser(id);
    }


    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO dto
    ) {
        return userService.updateProfile(id, dto);
    }
}
