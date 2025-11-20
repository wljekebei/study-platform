package client.dto;

public class UpdateTask {
    public String title;
    public String description;
    public String deadline;
    public String status;

    public UpdateTask(String title, String description, String deadline, String status) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }
}
