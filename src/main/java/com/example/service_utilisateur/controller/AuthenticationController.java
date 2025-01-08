package com.example.service_utilisateur.controller;

import com.example.service_utilisateur.dto.LoginResponse;
import com.example.service_utilisateur.dto.LoginUserDto;
import com.example.service_utilisateur.dto.RegisterUserDto;
import com.example.service_utilisateur.dto.UserDto;
import com.example.service_utilisateur.model.User;
import com.example.service_utilisateur.service.AuthenticationService;
import com.example.service_utilisateur.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {

        private final JwtService jwtService;
//    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

//    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
//        this.jwtService = jwtService;
//        this.authenticationService = authenticationService;
//    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        UserDto registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser,authenticatedUser.getId(),authenticatedUser.getRole());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
