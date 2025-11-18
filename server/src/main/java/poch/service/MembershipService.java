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

        List<Membership> exist = membershipRepository.findByUserId(dto.userId)
                .stream()
                .filter(m -> m.getGroupId().equals(dto.groupId))
                .toList();

        if (!exist.isEmpty()) {
            throw new RuntimeException("User already in this group");
        }

        Membership m = new Membership();
        m.setUserId(dto.userId);
        m.setGroupId(dto.groupId);
        m.setRole(dto.role);
        m.setJoinedAt(LocalDateTime.now());

        return membershipRepository.save(m);
    }
}
