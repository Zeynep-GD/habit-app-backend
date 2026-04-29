package HabitAppBackend.demo.domain.dto;

//Kullanıcının Alışkanlıklarını Göstermek İçin
public record UserHabitDTO(
        Long id,
        String name,
        String category,
        String iconName,
        Integer currentStreak,
        Integer longestStreak
) {}