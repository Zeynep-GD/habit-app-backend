package HabitAppBackend.demo.domain.dto;

import jakarta.validation.constraints.NotBlank;

//Yeni Alışkanlık Eklerken Kullanılır
public record UserHabitCreateRequest(
        @NotBlank(message = "Alışkanlık adı boş bırakılamaz")
        String name,

        @NotBlank(message = "Kategori seçimi zorunludur")
        String category,

        String iconName // İkon opsiyonel olabilir, boşsa Flutter'da varsayılan bir ikon gösteririz
) {}