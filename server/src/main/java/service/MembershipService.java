package service;

import entity.Membership;
import org.springframework.stereotype.Service;
import repository.MembershipRepository;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> getMembersByGroup(Long groupId) {
        return membershipRepository.findByGroup_id(groupId);
    }

    public List<Membership> getGroupsByUser(Long userId) {
        return membershipRepository.findByUser_id(userId);
    }

    public Membership save(Membership membership) {
        return membershipRepository.save(membership);
    }

    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }
}
