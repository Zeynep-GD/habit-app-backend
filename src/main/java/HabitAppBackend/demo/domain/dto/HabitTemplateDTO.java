package HabitAppBackend.demo.domain.dto;

//şablonlarını listelerken kullanılıyor
public record HabitTemplateDTO(
        Long id,
        String name,
        String category,
        String description,
        String iconName
) {}