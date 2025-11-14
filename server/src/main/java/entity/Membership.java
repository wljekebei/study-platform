package entity;
import jakarta.persistence.*;

@Entity
@Table(name = "memberships")
public class Membership {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membership_id;
    private Long user_id;
    private Long group_id;
    private String role;
    private String joined_at;

    public Membership(){}

    public Long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(Long membership_id) {
        this.membership_id = membership_id;
    }

    public String getJoined_at() {
        return joined_at;
    }

    public void setJoined_at(String joined_at) {
        this.joined_at = joined_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

}
