package client.models;

public class Group {
    private Long group_id;
    private String name;
    private String description;
    private Long created_by;
    private String created_at;

    public Group() {
    }

    public Group(Long group_id, String name, String description, Long created_by, String created_at) {
        this.group_id = group_id;
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.created_at = created_at;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
