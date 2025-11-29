package client.services;

import client.dto.UpdateTask;
import client.models.Task;

import java.util.List;

public class TaskAPI {

    private static final String BASE = "http://localhost:8080/tasks";

    public static List<Task> getByGroup(Long id) throws Exception {
        return Http.getList(BASE + "/group/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Task>>() {});
    }

    public static List<Task> getByCreator(Long id) throws Exception {
        return Http.getList(BASE + "/creator/" + id, new com.fasterxml.jackson.core.type.TypeReference<List<Task>>() {});
    }

    public static Task create(Task task) throws Exception {
        return Http.post(BASE, task, Task.class);
    }

    public static void delete(Long id) throws Exception {
        Http.delete(BASE + "/" + id);
    }

    public static Task update(Long id, String title, String description, String deadline, String status) throws Exception {
        UpdateTask req = new UpdateTask(title, description, deadline, status);
        return Http.put(BASE + "/" + id, req, Task.class);
    }

}
