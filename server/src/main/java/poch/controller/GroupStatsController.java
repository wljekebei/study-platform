package poch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poch.dto.GroupStatsDTO;
import poch.service.GroupStatsService;

@RestController
@RequestMapping("/stats/group")
public class GroupStatsController {

    private final GroupStatsService service;

    public GroupStatsController(GroupStatsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public GroupStatsDTO get(@PathVariable Long id) {
        return service.getStats(id);
    }
}
