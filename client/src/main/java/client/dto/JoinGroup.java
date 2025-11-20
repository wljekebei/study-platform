package client.dto;

public class JoinGroup {
    public  Long userId;
    public Long groupId;
    public String role;

    public JoinGroup(Long userId, Long groupId, String role) {
        this.userId = userId;
        this.groupId = groupId;
        this.role = role;
    }
}
