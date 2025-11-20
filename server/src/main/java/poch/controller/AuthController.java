package poch.controller;

import org.springframework.web.bind.annotation.*;
import poch.dto.UserLoginDTO;
import poch.dto.UserRegisterDTO;
import poch.dto.UserResponseDTO;
import poch.service.UserService;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserLoginDTO dto) {
        return userService.login(dto);
    }
}
