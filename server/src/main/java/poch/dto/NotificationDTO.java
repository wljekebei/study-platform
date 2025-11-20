package poch.dto;

public class NotificationDTO {
    public String type;   // TASK_CREATED, RESOURCE_ADDED, MEMBER_JOINED
    public String message;
    public Long groupId;

    public NotificationDTO(String type, String message, Long groupId) {
        this.type = type;
        this.message = message;
        this.groupId = groupId;
    }
}
