package HabitAppBackend.demo.services;

import HabitAppBackend.demo.domain.dto.AuthResponse;
import HabitAppBackend.demo.domain.dto.LoginRequest;
import HabitAppBackend.demo.domain.dto.RegisterRequest;
import HabitAppBackend.demo.domain.dto.UserDTO;

public interface AuthService {
    UserDTO register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}