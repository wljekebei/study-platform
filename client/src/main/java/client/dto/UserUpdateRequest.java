package client.dto;

public class UserUpdateRequest {
    public String email;
    public String password;

    public UserUpdateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
