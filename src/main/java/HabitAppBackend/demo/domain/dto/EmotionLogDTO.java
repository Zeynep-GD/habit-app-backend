package HabitAppBackend.demo.domain.dto;

import java.time.LocalDate;

public record EmotionLogDTO(
        Long id,
        LocalDate logDate,
        Integer joyRate,
        Integer sadnessRate,
        Integer angerRate,
        Integer fearRate,
        Integer disgustRate,
        Integer anxietyRate,
        Integer embarrassmentRate,
        Integer envyRate,
        Integer ennuiRate
) {}