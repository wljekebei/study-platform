package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.Membership;
import poch.repository.MembershipRepository;
import poch.dto.JoinGroupDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> getByUser(Long userId) {
        return membershipRepository.findByUserId(userId);
    }

    public List<Membership> getByGroup(Long groupId) {
        return membershipRepository.findByGroupId(groupId);
    }

    public Membership save(Membership membership) {
        return membershipRepository.save(membership);
    }

    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }

    public Membership joinGroup(JoinGroupDTO dto) {


        Membership exist = membershipRepository.findByUserIdAndGroupId(dto.userId, dto.groupId);

        if (exist != null) {
            throw new RuntimeException("User already in this group");
        }

        Membership m = new Membership();
        m.setUserId(dto.userId);
        m.setGroupId(dto.groupId);
        m.setRole(dto.role);
        m.setJoinedAt(LocalDateTime.now());

        return membershipRepository.save(m);
    }


    public void leaveGroup(Long userId, Long groupId) {
        Membership membership = membershipRepository.findByUserIdAndGroupId(userId, groupId);

        if (membership == null) {
            throw new RuntimeException("User is not in this group");
        }

        membershipRepository.delete(membership);
    }


    public void kickUser(Long userId, Long groupId) {
        leaveGroup(userId, groupId);
    }
}
