package client.util;

import client.models.Group;
import client.models.Task;
import client.models.User;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    public static List<Group> groups = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User(
                101L,
                "admin",
                "@.",
                "1"
        ));

        users.add(new User(
                102L,
                "Kolay",
                "kolay228@example.com",
                "abcd9876"
        ));

        users.add(new User(
                103L,
                "Vasyl",
                "fllesruoy@gmail.com",
                "qwerty555"
        ));

        users.add(new User(
                104L,
                "HEIKO228",
                "xheiko@stuba.sk",
                "pass9087"
        ));

        users.add(new User(
                105L,
                "dimka",
                "savin512@example.com",
                "secure7788"
        ));
    }

    static {
        groups.add(new Group(
                1L,
                List.of(
                        users.get(0), users.get(2), users.get(3), users.get(4)
                ),
                "2024-01-01",
                1L,
                "Default group",
                "ACSS"
        ));

        groups.add(new Group(
                2L,
                List.of(
                       users.get(0), users.get(1), users.get(4)
                ),
                "2024-01-01",
                3L,
                "Some desc",
                "TSIKT"
        ));
    }

    static {
        tasks.add(new Task(
                123L,
                1L,
                3L,
                "Finish UI Layout",
                "Complete the final version of the group screen layout.",
                "In progress",
                "2025-11-20",
                "2025-11-12"
        ));

        tasks.add(new Task(
                223L,
                1L,
                5L,
                "Database Setup",
                "Create tables for users, groups, tasks and relations.",
                "Open",
                "2025-11-25",
                "2025-11-13"
        ));

        tasks.add(new Task(
                323L,
                1L,
                3L,
                "Add Scroll Styling",
                "Improve design of scrollbars for the task table and user list.",
                "Done",
                "2025-11-15",
                "2025-11-10"
        ));

        tasks.add(new Task(
                324L,
                2L,
                5L,
                "Implement Login Validation",
                "Add server-side and client-side validation for login and registration forms.",
                "In progress",
                "2025-12-01",
                "2025-11-16"
        ));

        tasks.add(new Task(
                325L,
                2L,
                11L,
                "Optimize Group Loading",
                "Improve the loading speed of groups by caching memberships and tasks.",
                "Open",
                "2025-12-10",
                "2025-11-17"
        ));

    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static void setGroups(List<Group> groups) {
        MockDB.groups = groups;
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(List<Task> tasks) {
        MockDB.tasks = tasks;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        MockDB.users = users;
    }
}