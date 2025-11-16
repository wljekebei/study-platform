package client.models;

public class Membership {
    private Long membership_id;
    private Long user_id;
    private Long group_id;
    private String role;
    private String joined_at;

    public Membership() {
    }

    public Membership(Long membership_id, Long user_id, Long group_id, String role, String joined_at) {
        this.membership_id = membership_id;
        this.user_id = user_id;
        this.group_id = group_id;
        this.role = role;
        this.joined_at = joined_at;
    }

    public Long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(Long membership_id) {
        this.membership_id = membership_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoined_at() {
        return joined_at;
    }

    public void setJoined_at(String joined_at) {
        this.joined_at = joined_at;
    }
}
