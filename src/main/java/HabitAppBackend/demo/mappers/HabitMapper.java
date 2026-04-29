package HabitAppBackend.demo.mappers;

import HabitAppBackend.demo.domain.dto.HabitTemplateDTO;
import HabitAppBackend.demo.domain.dto.UserHabitCreateRequest;
import HabitAppBackend.demo.domain.dto.UserHabitDTO;
import HabitAppBackend.demo.domain.entities.HabitTemplate;
import HabitAppBackend.demo.domain.entities.UserHabit;

public class HabitMapper {

    // 1. Şablon Entity -> Şablon DTO (Ekranda listelemek için)
    public static HabitTemplateDTO toTemplateDto(HabitTemplate template) {
        return new HabitTemplateDTO(
                template.getId(),
                template.getName(),
                template.getCategory(),
                template.getDescription(),
                template.getIconName()
        );
    }

    // 2. Request -> UserHabit Entity (Kullanıcı yeni alışkanlık kaydederken)
    public static UserHabit fromCreateRequest(UserHabitCreateRequest request) {
        UserHabit userHabit = new UserHabit();
        userHabit.setName(request.name());
        userHabit.setCategory(request.category());
        userHabit.setIconName(request.iconName());
        // Not: currentStreak ve longestStreak Entity içinde default olarak 0 atandığı için burada yazmıyoruz.
        return userHabit;
    }

    // 3. UserHabit Entity -> UserHabit DTO (Kullanıcının kendi listesini dışarı dönerken)
    public static UserHabitDTO toUserHabitDto(UserHabit userHabit) {
        return new UserHabitDTO(
                userHabit.getId(),
                userHabit.getName(),
                userHabit.getCategory(),
                userHabit.getIconName(),
                userHabit.getCurrentStreak(),
                userHabit.getLongestStreak()
        );
    }
}