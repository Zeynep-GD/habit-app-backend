package HabitAppBackend.demo.services;

import HabitAppBackend.demo.domain.dto.HabitTemplateDTO;
import HabitAppBackend.demo.domain.dto.UserHabitCreateRequest;
import HabitAppBackend.demo.domain.dto.UserHabitDTO;

import java.util.List;

public interface HabitService {
    // --- ŞABLON (VİTRİN) İŞLEMLERİ ---
    List<HabitTemplateDTO> getAllTemplates();
    List<HabitTemplateDTO> getTemplatesByCategory(String category);

    // --- KULLANICI ALIŞKANLIK İŞLEMLERİ ---
    List<UserHabitDTO> getUserHabits(String userEmail);
    UserHabitDTO createUserHabit(String userEmail, UserHabitCreateRequest request);
}