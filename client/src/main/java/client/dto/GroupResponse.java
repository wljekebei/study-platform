package client.dto;

public class GroupResponse {
    public Long id;
    public String name;
    public String description;
    public Long createdBy;
    public String createdAt;

    public GroupResponse(Long id, String name, String description, Long createdBy, String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
