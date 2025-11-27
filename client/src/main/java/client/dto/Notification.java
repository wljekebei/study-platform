package client.dto;

public class Notification {
    public String type;
    public String message;
    public Long groupId;

    public Notification() {
    }

    public Notification(String type, String message, Long groupId) {
        this.type = type;
        this.message = message;
        this.groupId = groupId;
    }
}
