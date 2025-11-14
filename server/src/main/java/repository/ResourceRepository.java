package repository;

import entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByGroup_id(Long groupId);

    List<Resource> findByUploaded_by(Long uploadedBy);
}