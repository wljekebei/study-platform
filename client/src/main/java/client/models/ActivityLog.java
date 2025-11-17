package client.models;

public class ActivityLog {
    private Long log_id;
    private Long user_id;
    private String action;
    private String timestamp;
    private String details;

    public ActivityLog() {
    }

    public ActivityLog(Long user_id, Long log_id, String action, String timestamp, String details) {
        this.user_id = user_id;
        this.log_id = log_id;
        this.action = action;
        this.timestamp = timestamp;
        this.details = details;
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
