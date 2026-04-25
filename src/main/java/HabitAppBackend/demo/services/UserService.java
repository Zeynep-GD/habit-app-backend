package HabitAppBackend.demo.services;


import HabitAppBackend.demo.domain.dto.UserDTO;

import java.util.UUID;

public interface UserService {
    UserDTO getUserDtoById(UUID id);
    UserDTO getUserDtoByEmail(String email);
}