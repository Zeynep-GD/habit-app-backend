package HabitAppBackend.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "E-posta alanı boş bırakılamaz")
        @Email(message = "Geçerli bir e-posta adresi giriniz")
        String email,

        @NotBlank(message = "Şifre alanı boş bırakılamaz")
        @Size(min = 6, message = "Şifre en az 6 karakter uzunluğunda olmalıdır")
        String password
) {}