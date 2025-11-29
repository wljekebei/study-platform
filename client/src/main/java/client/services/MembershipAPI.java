package client.services;

import client.dto.GroupResponse;
import client.dto.JoinGroup;
import client.models.Membership;

import java.util.List;

public class MembershipAPI {

    private static final String BASE = "http://localhost:8080/memberships";

    public static List<Membership> getByUser(Long id) throws Exception {
        return Http.getList(BASE + "/user/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Membership>>() {});
    }

    public static List<Membership> getByGroup(Long id) throws Exception {
        return Http.getList(BASE + "/group/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Membership>>() {});
    }

    public static Membership create(Membership membership) throws Exception {
        return Http.post(BASE, membership, Membership.class);
    }

    public static Membership join(Long userId, Long groupId, String role) throws Exception {
        JoinGroup req = new JoinGroup(userId, groupId, role);
        return Http.post(BASE + "/join", req, Membership.class);
    }

    public static void leave(Long userId, Long groupId) throws Exception {
        String url = BASE + "/leave?userId=" + userId + "&groupId=" + groupId;
        Http.postVoid(url, null);
    }

    public static void kick(Long groupId, Long userId) throws Exception {
        Http.delete(BASE + "/group/" + groupId + "/user/" + userId);
    }

}
