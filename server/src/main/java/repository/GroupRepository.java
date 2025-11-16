package repository;

import entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByCreated_by(Long created_by);
}
