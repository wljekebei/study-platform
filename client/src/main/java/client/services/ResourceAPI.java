package client.services;

import client.dto.LinkRequest;
import client.models.Resource;

import java.io.File;
import java.util.List;

public class ResourceAPI {

    private static final String BASE = "http://localhost:8080/resources";

    public static List<Resource> getByGroup(Long id) throws Exception {
        return Http.getList(BASE + "/group/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Resource>>() {});
    }

    public static List<Resource> getByUser(Long id) throws Exception {
        return Http.getList(BASE + "/user/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Resource>>() {});
    }

    public static Resource getById(Long id) throws Exception {
        return Http.get(BASE + "/" + id, Resource.class);
    }

    public static Resource create(Resource resource) throws Exception {
        return Http.post(BASE, resource, Resource.class);
    }

    public static void delete(Long id) throws Exception {
        Http.delete(BASE + "/" + id);
    }

    public static Resource addLink(Long groupId, Long uploadedBy, String title, String url) throws Exception {
        LinkRequest req = new LinkRequest(groupId, uploadedBy, title, url);
        return Http.post(BASE + "/link", req, Resource.class);
    }

    public static Resource uploadFile(Long groupId, Long uploadedBy, String title, File file) throws Exception {
        return Http.uploadFile(BASE + "/file", groupId, uploadedBy, title, file);
    }

}
