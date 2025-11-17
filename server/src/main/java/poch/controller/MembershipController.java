package poch.controller;

import org.springframework.web.bind.annotation.*;
import poch.entity.Membership;
import poch.service.MembershipService;

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        membershipService.delete(id);
    }
}
