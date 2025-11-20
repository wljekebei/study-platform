package client.services;

import client.dto.UserStats;

public class UserStatsAPI {

    private static final String BASE = "http://localhost:8080/stats/user";

    public static UserStats get(Long id) throws Exception {
        return Http.get(BASE + "/" + id, UserStats.class);
    }

}
