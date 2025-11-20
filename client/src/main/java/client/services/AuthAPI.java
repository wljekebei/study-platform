package client.services;

import client.dto.RegisterRequest;
import client.dto.LoginRequest;
import client.models.User;

public class AuthAPI {

    private static final String BASE = "http://localhost:8080/auth";

    public static User register(String name, String email, String password) throws Exception {
        RegisterRequest req = new RegisterRequest(name, email, password);
        return Http.post(BASE + "/register", req, User.class);
    }

    public static User login(String email, String password) throws Exception {
        LoginRequest req = new LoginRequest(email, password);
        return Http.post(BASE + "/login", req, User.class);
    }
}
