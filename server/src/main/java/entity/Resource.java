package entity;
import jakarta.persistence.*;
@Entity
@Table(name = "resources")

public class Resource {
    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public long getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(long uploaded_by) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resource_id;
    private long group_id;
    private long uploaded_by;
    private String title;
    private String type;
    private String path_or_url;
    private String uploaded_at;
        public Resource() {}

}
