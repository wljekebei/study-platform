package client.models;

import java.util.List;

public class Group {
    private Long group_id;
    private String name;
    private String description;
    private Long created_by;
    private String created_at;
    private List<User> members;

    public Group() {
    }

    public Group(Long group_id, List<User> members, String created_at, Long created_by, String description, String name) {
        this.group_id = group_id;
        this.members = members;
        this.created_at = created_at;
        this.created_by = created_by;
        this.description = description;
        this.name = name;
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
