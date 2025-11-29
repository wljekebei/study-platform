package poch.service;

import org.springframework.stereotype.Service;
import poch.dto.NotificationDTO;
import poch.entity.Resource;
import poch.repository.ResourceRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final NotificationService notificationService;

    public ResourceService(ResourceRepository resourceRepository,
                           NotificationService notificationService) {
        this.resourceRepository = resourceRepository;
        this.notificationService = notificationService;
    }

    public List<Resource> getResourcesByGroup(Long groupId) {
        return resourceRepository.findByGroupId(groupId);
    }

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }

    public Resource addLink(Long groupId, Long uploadedBy, String title, String url) {
        Resource r = new Resource();
        r.setGroupId(groupId);
        r.setUploadedBy(uploadedBy);
        r.setTitle(title);
        r.setType("LINK");
        r.setPathOrUrl(url);
        r.setUploadedAt(LocalDateTime.now().toString());

        Resource saved = resourceRepository.save(r);

        notificationService.sendToGroup(
                saved.getGroupId(),
                new NotificationDTO(
                        "RESOURCE_ADDED",
                        "New link: " + saved.getTitle(),
                        saved.getGroupId()
                )
        );

        return saved;
    }

    public Resource uploadFile(Long groupId, Long uploadedBy, String title, MultipartFile file) throws IOException {

        String baseDir = System.getProperty("user.home") + File.separator + "collabstudy_uploads";
        File folder = new File(baseDir);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("Cannot create upload directory: " + baseDir);
            }
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File targetFile = new File(folder, fileName);

        file.transferTo(targetFile);

        Resource r = new Resource();
        r.setGroupId(groupId);
        r.setUploadedBy(uploadedBy);
        r.setTitle(title);
        r.setType("FILE");
        r.setPathOrUrl(targetFile.getAbsolutePath());
        r.setUploadedAt(LocalDateTime.now().toString());

        Resource saved = resourceRepository.save(r);

        notificationService.sendToGroup(
                saved.getGroupId(),
                new NotificationDTO(
                        "RESOURCE_ADDED",
                        "New file: " + saved.getTitle(),
                        saved.getGroupId()
                )
        );

        return saved;
    }
}
