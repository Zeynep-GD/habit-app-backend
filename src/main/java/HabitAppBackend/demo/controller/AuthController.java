package HabitAppBackend.demo.controller;

import HabitAppBackend.demo.domain.dto.AuthResponse;
import HabitAppBackend.demo.domain.dto.LoginRequest;
import HabitAppBackend.demo.domain.dto.RegisterRequest;
import HabitAppBackend.demo.domain.dto.UserDTO;
import HabitAppBackend.demo.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO registeredUser = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}