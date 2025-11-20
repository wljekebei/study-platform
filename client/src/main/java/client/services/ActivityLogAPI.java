package client.services;

import client.models.ActivityLog;

import java.util.List;

public class ActivityLogAPI {

    private static final String BASE = "http://localhost:8080/activity";

    public static List<ActivityLog> getByUser(Long id) throws Exception {
        return Http.getList(BASE + "/user/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<ActivityLog>>() {});
    }

    public static ActivityLog create(ActivityLog log) throws Exception {
        return Http.post(BASE, log, ActivityLog.class);
    }

    public static void delete(Long id) throws Exception {
        Http.delete(BASE + "/" + id);
    }

}
