package poch.service;

import poch.dto.UserUpdateDTO;
import poch.entity.User;
import org.springframework.stereotype.Service;
import poch.repository.UserRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import poch.dto.UserRegisterDTO;
import poch.dto.UserLoginDTO;
import poch.dto.UserResponseDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public UserResponseDTO register(UserRegisterDTO dto) {


        if (userRepository.findByEmail(dto.email).isPresent()) {
            throw new RuntimeException("Email already taken");
        }

        User user = new User();
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setPasswordHash(passwordEncoder.encode(dto.password)); // хешируем

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }


    public UserResponseDTO login(UserLoginDTO dto) {

        User user = userRepository.findByEmail(dto.email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!passwordEncoder.matches(dto.password, user.getPasswordHash())) {
            throw new RuntimeException("Wrong password");
        }

        return mapToResponse(user);
    }

    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.email = user.getEmail();
        return dto;
    }

    public UserResponseDTO updateProfile(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.name);
        user.setEmail(dto.email);

        user = userRepository.save(user);
        return mapToResponse(user);
    }

}
