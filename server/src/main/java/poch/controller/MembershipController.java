package poch.controller;

import org.springframework.web.bind.annotation.*;
import poch.entity.Membership;
import poch.service.MembershipService;
import poch.dto.JoinGroupDTO;

import java.util.List;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/user/{id}")
    public List<Membership> getByUser(@PathVariable Long id) {
        return membershipService.getByUser(id);
    }

    @GetMapping("/group/{id}")
    public List<Membership> getByGroup(@PathVariable Long id) {
        return membershipService.getByGroup(id);
    }

    @PostMapping
    public Membership create(@RequestBody Membership membership) {
        return membershipService.save(membership);
    }

    @PostMapping("/join")
    public Membership join(@RequestBody JoinGroupDTO dto) {
        return membershipService.joinGroup(dto);
    }

    @PostMapping("/leave")
    public void leave(@RequestParam Long userId, @RequestParam Long groupId) {
        membershipService.leaveGroup(userId, groupId);
    }

    @DeleteMapping("/group/{groupId}/user/{userId}")
    public void kick(@PathVariable Long groupId, @PathVariable Long userId) {
        membershipService.kickUser(userId, groupId);
    }

}

