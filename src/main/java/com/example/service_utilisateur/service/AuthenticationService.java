package com.example.service_utilisateur.service;

import com.example.service_utilisateur.client.CmiClient;
import com.example.service_utilisateur.config.UserMapper;
import com.example.service_utilisateur.dto.LoginUserDto;
import com.example.service_utilisateur.dto.RegisterUserDto;
import com.example.service_utilisateur.dto.UserDto;
import com.example.service_utilisateur.model.Role;
import com.example.service_utilisateur.model.User;
import com.example.service_utilisateur.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final CmiClient cmiClient;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto signup(RegisterUserDto input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(input.getRole());
        User savedUser = userRepository.save(user);

        // Assigner le compte bancaire si le rôle est CLIENT
        if (user.getRole() == Role.CLIENT) {
            this.cmiClient.assignerUtilisateur(input.getNrCompteBancaire(), savedUser.getId());
        }

        return userMapper.toDTO(savedUser);
    }

    public User authenticate(LoginUserDto input) {
        // Récupérer l'utilisateur depuis la base de données
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return user;
    }
}
