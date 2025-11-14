package repository;

import entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByUser_id(Long userId);

    List<Membership> findByGroup_id(Long groupId);
}
