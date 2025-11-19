package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.Resource;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByGroupId(Long groupId);

    List<Resource> findByUploadedBy(Long uploadedBy);


}
