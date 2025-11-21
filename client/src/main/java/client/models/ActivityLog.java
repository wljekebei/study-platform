package client.models;

public class ActivityLog {
    private Long logId;
    private Long userId;
    private String action;
    private String timestamp;
    private String details;

    public ActivityLog() {
    }

    public ActivityLog(Long userId, Long logId, String action, String timestamp, String details) {
        this.userId = userId;
        this.logId = logId;
        this.action = action;
        this.timestamp = timestamp;
        this.details = details;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
