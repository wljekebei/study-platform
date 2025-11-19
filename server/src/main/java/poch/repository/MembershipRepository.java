package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poch.entity.Membership;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByUserId(Long userId);

    List<Membership> findByGroupId(Long groupId);

    Membership findByUserIdAndGroupId(Long userId, Long groupId);
}
