package poch.dto;

public class NotificationDTO {
    public String type;
    public String message;
    public Long groupId;

    public NotificationDTO(String type, String message, Long groupId) {
        this.type = type;
        this.message = message;
        this.groupId = groupId;
    }
}
