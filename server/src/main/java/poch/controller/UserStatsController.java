package poch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poch.dto.UserStatsDTO;
import poch.service.UserStatsService;

@RestController
@RequestMapping("/stats/user")

public class UserStatsController {

    private final UserStatsService service;

    public UserStatsController(UserStatsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserStatsDTO get(@PathVariable Long id) {
        return service.getStats(id);
    }
}
