package HabitAppBackend.demo.mappers;


import HabitAppBackend.demo.domain.dto.RegisterRequest;
import HabitAppBackend.demo.domain.dto.UserDTO;
import HabitAppBackend.demo.domain.entities.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
    public static User fromRegisterRequest(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setRole("USER");
        return user;
    }
}
