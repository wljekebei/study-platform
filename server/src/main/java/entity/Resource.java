package entity;
import jakarta.persistence.*;
@Entity
@Table(name = "resources")

public class Resource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_id;
    private Long group_id;
    private Long uploaded_by;
    private String title;
    private String type;
    private String path_or_url;
    private String uploaded_at;
    public Resource() {}

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(Long uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath_or_url() {
        return path_or_url;
    }

    public void setPath_or_url(String path_or_url) {
        this.path_or_url = path_or_url;
    }

    public String getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(String uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

}
