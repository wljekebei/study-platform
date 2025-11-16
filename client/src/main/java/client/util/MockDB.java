package client.util;

import client.models.Group;
import client.models.User;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    public static List<Group> groups = new ArrayList<>();

    static {
        groups.add(new Group(
                1L,
                List.of(
                        new User(1L, "Kolay", "k@a.com", "abcde"),
                        new User(2L, "Vasyl", "v@a.com", "abcf"),
                        new User(3L, "Random", "r@a.com", "bcdef"),
                        new User(4L, "Timur", "t@a.com", "acdef")
                ),
                "2024-01-01",
                1L,
                "Default group",
                "ACSS"
        ));

        groups.add(new Group(
                2L,
                List.of(
                        new User(10L, "HEIKO228", "h@a.com", "abcef"),
                        new User(11L, "Andrianka", "a@a.com", "abdef")
                ),
                "2024-01-01",
                3L,
                "Some desc",
                "TSIKT"
        ));
    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static void setGroups(List<Group> groups) {
        MockDB.groups = groups;
    }
}