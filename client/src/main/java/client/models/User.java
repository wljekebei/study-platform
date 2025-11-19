package client.models;

import java.util.Objects;

public class User {
    private Long user_id;
    private String name;
    private String email;

    public User() {
    }

    public User(Long user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUser_id(), user.getUser_id());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUser_id());
    }
}
