package HabitAppBackend.demo.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String email,
        String role,
        LocalDateTime createdAt
) {}
