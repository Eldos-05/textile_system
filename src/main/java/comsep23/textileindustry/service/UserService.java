package comsep23.textileindustry.service;

import comsep23.textileindustry.config.Role;
import comsep23.textileindustry.entity.User;
import comsep23.textileindustry.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void assignRole(User user, String role) {
        switch (role.toUpperCase()) {
            case "SALEMAN":
                user.setRole(Role.ROLE_SALEMAN);
                break;
            case "DELIVERYMAN":
                user.setRole(Role.ROLE_DELIVERYMAN);
                break;
            case "PROVIDER":
                user.setRole(Role.ROLE_PROVIDER);
                break;
            case "CUSTOMER":
                user.setRole(Role.ROLE_CUSTOMER);
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
