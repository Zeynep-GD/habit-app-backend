package HabitAppBackend.demo.services.impl;

import HabitAppBackend.demo.domain.dto.AuthResponse;
import HabitAppBackend.demo.domain.dto.LoginRequest;
import HabitAppBackend.demo.domain.dto.RegisterRequest;
import HabitAppBackend.demo.domain.dto.UserDTO;
import HabitAppBackend.demo.domain.entities.User;
import HabitAppBackend.demo.exception.ResourceAlreadyExistsException;
import HabitAppBackend.demo.exception.UnauthorizedException;
import HabitAppBackend.demo.mappers.UserMapper;
import HabitAppBackend.demo.repositories.UserRepository;
import HabitAppBackend.demo.security.JwtService;
import HabitAppBackend.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDTO register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("Bu e-posta adresi zaten sistemde kayıtlı.");
        }

        User user = UserMapper.fromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UnauthorizedException("Hatalı e-posta veya şifre"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("Hatalı e-posta veya şifre");
        }

        UserDTO userDto = UserMapper.toDto(user);
        String jwtToken = jwtService.generateToken(user); // VIP Kart basıldı!

        return new AuthResponse(userDto, jwtToken);
    }
}