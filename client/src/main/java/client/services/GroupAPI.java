package client.services;

import client.dto.GroupCreate;
import client.dto.GroupResponse;
import client.dto.GroupUpdate;
import client.models.Group;
import client.models.User;

import java.util.List;

public class GroupAPI {

    private static final String BASE = "http://localhost:8080/groups";

    public static void delete(Long id) throws Exception {
        Http.delete(BASE + "/" + id);
    }

    public static List<GroupResponse> getAll() throws Exception {
        return Http.getList(BASE, new com.fasterxml.jackson.core.type.TypeReference<List<GroupResponse>>() {});
    }

    public static Group getById(Long id) throws Exception {
        return Http.get(BASE + "/" + id, Group.class);
    }

    public static Group create(String name, String description, Long createdBy) throws Exception {
        GroupCreate req = new GroupCreate(name, description, createdBy);
        return Http.post(BASE + "/create", req, Group.class);
    }

    public static GroupResponse update(Long id, String name, String description) throws Exception {
        GroupUpdate req = new GroupUpdate(name, description);
        return Http.put(BASE + "/" + id, req, GroupResponse.class);
    }
}
