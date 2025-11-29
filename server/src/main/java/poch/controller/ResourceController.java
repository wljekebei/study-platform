package poch.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poch.dto.LinkRequestDTO;
import poch.entity.Resource;
import poch.service.ResourceService;
import java.io.IOException;


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



    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return resourceService.save(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.delete(id);
    }
    @PostMapping("/link")
    public Resource addLink(@RequestBody LinkRequestDTO req) {
        return resourceService.addLink(
                req.groupId,
                req.uploadedBy,
                req.title,
                req.url
        );
    }


    @PostMapping("/file")
    public Resource uploadFile(
            @RequestParam Long groupId,
            @RequestParam Long uploadedBy,
            @RequestParam String title,
            @RequestParam MultipartFile file
    ) throws IOException {
        return resourceService.uploadFile(groupId, uploadedBy, title, file);
    }
}
