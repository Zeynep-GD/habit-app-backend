package HabitAppBackend.demo.services.impl;

import HabitAppBackend.demo.domain.dto.HabitTemplateDTO;
import HabitAppBackend.demo.domain.dto.UserHabitCreateRequest;
import HabitAppBackend.demo.domain.dto.UserHabitDTO;
import HabitAppBackend.demo.domain.entities.User;
import HabitAppBackend.demo.domain.entities.UserHabit;
import HabitAppBackend.demo.exception.ResourceNotFoundException;
import HabitAppBackend.demo.mappers.HabitMapper;
import HabitAppBackend.demo.repositories.HabitTemplateRepository;
import HabitAppBackend.demo.repositories.UserHabitRepository;
import HabitAppBackend.demo.repositories.UserRepository;
import HabitAppBackend.demo.services.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitTemplateRepository templateRepository;
    private final UserHabitRepository userHabitRepository;
    private final UserRepository userRepository;

    // 1. Sistemdeki tüm şablonları getirir
    @Override
    public List<HabitTemplateDTO> getAllTemplates() {
        return templateRepository.findAll().stream()
                .map(HabitMapper::toTemplateDto)
                .collect(Collectors.toList());
    }

    // 2. Sadece belirli bir kategorideki (Örn: "Sağlık") şablonları getirir
    @Override
    public List<HabitTemplateDTO> getTemplatesByCategory(String category) {
        return templateRepository.findByCategory(category).stream()
                .map(HabitMapper::toTemplateDto)
                .collect(Collectors.toList());
    }

    // 3. İçeri giren kullanıcının (token'dan gelen email) kendi alışkanlıklarını getirir
    @Override
    public List<UserHabitDTO> getUserHabits(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));

        return userHabitRepository.findByUser(user).stream()
                .map(HabitMapper::toUserHabitDto)
                .collect(Collectors.toList());
    }

    // 4. Kullanıcıya yepyeni bir alışkanlık oluşturur ve profiline bağlar
    @Override
    public UserHabitDTO createUserHabit(String userEmail, UserHabitCreateRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));

        // DTO'yu Entity'e çevir
        UserHabit userHabit = HabitMapper.fromCreateRequest(request);

        // Alışkanlığın sahibini belirle! (Bu çok önemli, yoksa veritabanı hata verir)
        userHabit.setUser(user);

        // Veritabanına kaydet
        UserHabit savedHabit = userHabitRepository.save(userHabit);

        // Güvenli DTO formatında dışarı dön
        return HabitMapper.toUserHabitDto(savedHabit);
    }
}