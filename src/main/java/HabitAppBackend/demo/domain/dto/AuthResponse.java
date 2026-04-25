package HabitAppBackend.demo.domain.dto;

public record AuthResponse(
        UserDTO user,
        String accessToken
) {}