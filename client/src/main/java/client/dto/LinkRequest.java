package client.dto;

public class LinkRequest {

    public Long groupId;
    public Long uploadedBy;
    public String title;
    public String url;

    public LinkRequest(Long groupId, Long uploadedBy, String title, String url) {
        this.groupId = groupId;
        this.uploadedBy = uploadedBy;
        this.title = title;
        this.url = url;
    }
}
