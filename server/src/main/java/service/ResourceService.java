package service;

import entity.Resource;
import org.springframework.stereotype.Service;
import repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getResourcesByGroup(Long groupId) {
        return resourceRepository.findByGroup_id(groupId);
    }

    public List<Resource> getResourcesByUser(Long userId) {
        return resourceRepository.findByUploaded_by(userId);
    }

    // 🔥 Добавил метод с нижним подчёркиванием
    public Resource getBy_id(Long resource_id) {
        return resourceRepository.findById(resource_id).orElse(null);
    }

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
}
