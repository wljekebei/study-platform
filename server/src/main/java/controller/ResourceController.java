package controller;

import entity.Resource;
import org.springframework.web.bind.annotation.*;
import service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/group/{id}")
    public List<Resource> getByGroup(@PathVariable Long id) {
        return resourceService.getResourcesByGroup(id);
    }

    @GetMapping("/user/{id}")
    public List<Resource> getByUser(@PathVariable Long id) {
        return resourceService.getResourcesByUser(id);
    }

    @GetMapping("/{id}")
    public Resource getById(@PathVariable Long id) {
        return resourceService.getBy_id(id);
    }

    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return resourceService.save(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.delete(id);
    }
}
