package client.util;

import client.models.Group;
import client.models.Resource;
import client.models.Task;
import client.models.User;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    public static List<Group> groups = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Resource> resources = new ArrayList<>();

    static {
        users.add(new User(
                101L,
                "admin",
                "@."
        ));

        users.add(new User(
                102L,
                "Kolay",
                "kolay228@example.com"
        ));

        users.add(new User(
                103L,
                "Vasyl",
                "fllesruoy@gmail.com"
        ));

        users.add(new User(
                104L,
                "HEIKO228",
                "xheiko@stuba.sk"
        ));

        users.add(new User(
                105L,
                "dimka",
                "savin512@example.com"
        ));
    }

    static {
        groups.add(new Group(
                1L,
                List.of(
                        users.get(0), users.get(2), users.get(3), users.get(4)
                ),
                "2024-01-01",
                101L,
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

    static {
        resources.add(new Resource(
                1L, 1L, 10L,
                "Lecture Notes 1",
                "file",
                "/files/lecture1.pdf",
                "2025-01-10 14:22"
        ));

        resources.add(new Resource(
                2L, 1L, 11L,
                "Group Schedule",
                "file",
                "/files/schedule.xlsx",
                "2025-01-12 09:30"
        ));

        resources.add(new Resource(
                3L, 2L, 12L,
                "Project Requirements",
                "file",
                "/files/project.docx",
                "2025-01-14 17:45"
        ));

        resources.add(new Resource(
                4L, 2L, 10L,
                "UI Design PNG",
                "file",
                "/files/design.png",
                "2025-01-15 11:00"
        ));

        resources.add(new Resource(
                5L, 1L, 13L,
                "JavaFX Tutorial",
                "link",
                "https://openjfx.io/",
                "2025-01-16 09:10"
        ));

        resources.add(new Resource(
                6L, 1L, 14L,
                "Spring Boot Docs",
                "link",
                "https://spring.io/projects/spring-boot",
                "2025-01-16 09:12"
        ));

        resources.add(new Resource(
                7L, 3L, 12L,
                "Intro to Git",
                "link",
                "https://www.atlassian.com/git/tutorials",
                "2025-01-17 18:30"
        ));

        resources.add(new Resource(
                8L, 3L, 11L,
                "Database Normalization",
                "link",
                "https://www.geeksforgeeks.org/normalization-in-dbms/",
                "2025-01-18 10:55"
        ));

        resources.add(new Resource(
                9L, 2L, 14L,
                "Kotlin for Beginners",
                "link",
                "https://kotlinlang.org/docs/home.html",
                "2025-01-19 21:10"
        ));

        resources.add(new Resource(
                10L, 1L, 10L,
                "Java Concurrency Guide",
                "link",
                "https://docs.oracle.com/javase/tutorial/essential/concurrency/",
                "2025-01-20 08:25"
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

    public static List<Resource> getResources() {
        return resources;
    }

    public static void setResources(List<Resource> resources) {
        MockDB.resources = resources;
    }
}