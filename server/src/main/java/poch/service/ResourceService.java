package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.Resource;
import poch.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getResourcesByGroup(Long groupId) {
        return resourceRepository.findByGroupId(groupId);
    }

    public List<Resource> getResourcesByUser(Long userId) {
        return resourceRepository.findByUploadedBy(userId);
    }

    public Resource getById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
}

