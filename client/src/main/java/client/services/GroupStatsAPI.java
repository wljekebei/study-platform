package client.services;

import client.dto.GroupStats;

public class GroupStatsAPI {

    private static final String BASE = "http://localhost:8080/stats/group";

    public static GroupStats get(Long id) throws Exception {
        return Http.get(BASE + "/" + id, GroupStats.class);
    }

}
