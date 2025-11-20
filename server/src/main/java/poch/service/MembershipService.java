package poch.service;
import poch.entity.Group;
import org.springframework.stereotype.Service;
import poch.dto.JoinGroupDTO;
import poch.dto.NotificationDTO;
import poch.entity.Membership;
import poch.repository.GroupRepository;
import poch.repository.MembershipRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final NotificationService notificationService;
    private final GroupRepository groupRepository;


    public MembershipService(MembershipRepository membershipRepository,
                             NotificationService notificationService,
                             GroupRepository groupRepository) {
        this.membershipRepository = membershipRepository;
        this.notificationService = notificationService;
        this.groupRepository = groupRepository;
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
    public List<Group> getGroupsOfUser(Long userId) {
        List<Long> groupIds = membershipRepository.findGroupIdsByUserId(userId);
        return groupRepository.findAllById(groupIds);
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

        Membership saved = membershipRepository.save(m);


        notificationService.sendToGroup(
                saved.getGroupId(),
                new NotificationDTO(
                        "MEMBER_JOINED",
                        "Nový člen sa pridal do skupiny",
                        saved.getGroupId()
                )
        );

        return saved;
    }

    public void leaveGroup(Long userId, Long groupId) {
        Membership membership = membershipRepository.findByUserIdAndGroupId(userId, groupId);

        if (membership == null) {
            throw new RuntimeException("User is not in this group");
        }

        membershipRepository.delete(membership);


        notificationService.sendToGroup(
                groupId,
                new NotificationDTO(
                        "MEMBER_LEFT",
                        "Člen opustil skupinu",
                        groupId
                )
        );
    }

    public void kickUser(Long userId, Long groupId) {
        leaveGroup(userId, groupId);


    }
}

