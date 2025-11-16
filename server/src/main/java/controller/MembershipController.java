package controller;

import entity.Membership;
import org.springframework.web.bind.annotation.*;
import service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/group/{id}")
    public List<Membership> getMembersByGroup(@PathVariable Long id) {
        return membershipService.getMembersByGroup(id);
    }

    @GetMapping("/user/{id}")
    public List<Membership> getGroupsByUser(@PathVariable Long id) {
        return membershipService.getGroupsByUser(id);
    }

    @PostMapping
    public Membership create(@RequestBody Membership membership) {
        return membershipService.save(membership);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        membershipService.delete(id);
    }
}
