package client.models;

public class Membership {
    private Long membershipId;
    private Long userId;
    private Long groupId;
    private String role;
    private String joinedAt;

    public Membership() {
    }

    public Membership(Long membershipId, Long userId, Long groupId, String role, String joinedAt) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.groupId = groupId;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }
}
