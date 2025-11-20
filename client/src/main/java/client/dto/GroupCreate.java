package client.dto;

public class GroupCreate {
    public String name;
    public String description;
    public Long createdBy;

    public GroupCreate(String name, String description, Long createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }
}
