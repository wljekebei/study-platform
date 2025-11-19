package poch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poch.entity.Membership;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByUserId(Long userId);

    List<Membership> findByGroupId(Long groupId);

    Membership findByUserIdAndGroupId(Long userId, Long groupId);
    long countByUserId(Long userId);

    long countByGroupId(Long groupId);

    @Query("SELECT m.groupId FROM Membership m WHERE m.userId = :userId")
    List<Long> findGroupIdsByUserId(Long userId);

}
