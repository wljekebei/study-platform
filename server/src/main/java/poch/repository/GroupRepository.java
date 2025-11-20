package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByCreatedBy(Long createdBy);
}
