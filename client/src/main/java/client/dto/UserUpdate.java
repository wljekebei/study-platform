package client.dto;

public class UserUpdate {
    public String name;
    public String email;

    public UserUpdate(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
