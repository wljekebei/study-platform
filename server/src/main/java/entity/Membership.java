package entity;
import jakarta.persistence.*;


@Entity
@Table(name = "memberships")
public class Membership {
    public long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(long membership_id) {
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

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long membership_id;
    private long user_id;
    private long group_id;
    private String role;
    private String joined_at;

        public Membership(){}

}
