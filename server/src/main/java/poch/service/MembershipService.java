package poch.service;

import org.springframework.stereotype.Service;
import poch.entity.Membership;
import poch.repository.MembershipRepository;

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
}
