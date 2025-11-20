package client.services;

import client.dto.UserResponse;
import client.dto.UserUpdate;
import client.models.Group;
import client.models.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserAPI {

    private static final String BASE = "http://localhost:8080/users";

    public static UserResponse update(Long id, String name, String email) throws Exception {
        UserUpdate req = new UserUpdate(name, email);
        return Http.put(BASE + "/" + id, req, UserResponse.class);
    }

    public static List<User> getAll() throws Exception {
        return Http.getList(BASE, new com.fasterxml.jackson.core.type.TypeReference<List<User>>() {});
    }

    public static User getById(Long id) throws Exception {
        return Http.get(BASE + "/" + id, User.class);
    }

    public static List<Group> getUserGroups(Long id) throws Exception {
        return Http.getList(BASE + "/" + id + "/groups", new com.fasterxml.jackson.core.type.TypeReference<List<Group>>() {});
    }

    public static User getByEmail(String email) throws Exception {
        String encoded = java.net.URLEncoder.encode(email, StandardCharsets.UTF_8);
        return Http.get(BASE + "/email/" + encoded, User.class);
    }

    public static User create(User user) throws Exception {
        return Http.post(BASE, user, User.class);
    }

    public static void delete(Long id) throws Exception {
        Http.delete(BASE + "/" + id);
    }
}