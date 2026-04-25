package HabitAppBackend.demo.services.impl;

import HabitAppBackend.demo.domain.dto.UserDTO;
import HabitAppBackend.demo.exception.ResourceNotFoundException;
import HabitAppBackend.demo.mappers.UserMapper;
import HabitAppBackend.demo.repositories.UserRepository;
import HabitAppBackend.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserDtoById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Belirtilen ID'ye sahip kullanıcı bulunamadı: " + id));
    }

    @Override
    public UserDTO getUserDtoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Belirtilen e-posta adresine sahip kullanıcı bulunamadı: " + email));
    }

    // NOT: Kayıt (Register) işlemi artık AuthServiceImpl içinde güvenli bir şekilde yapılıyor.
    // Burası sadece kullanıcı bilgilerini getirmek (Profile vs.) için kullanılacak.
}