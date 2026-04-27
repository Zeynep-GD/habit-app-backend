package HabitAppBackend.demo.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmotionLogRequest(
        @NotNull(message = "Tarih alanı boş bırakılamaz")
        LocalDate logDate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer joyRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer sadnessRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer angerRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer fearRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer disgustRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer anxietyRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer embarrassmentRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer envyRate,

        @Min(value = 0, message = "Değer 0'dan küçük olamaz")
        @Max(value = 100, message = "Değer 100'den büyük olamaz")
        Integer ennuiRate
) {}