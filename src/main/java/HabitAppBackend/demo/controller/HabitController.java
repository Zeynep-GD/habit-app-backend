package HabitAppBackend.demo.controller;

import HabitAppBackend.demo.domain.dto.HabitTemplateDTO;
import HabitAppBackend.demo.domain.dto.UserHabitCreateRequest;
import HabitAppBackend.demo.domain.dto.UserHabitDTO;
import HabitAppBackend.demo.services.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits") // Ana kapımız
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    // ==========================================
    // 1. ŞABLON (VİTRİN) İŞLEMLERİ
    // ==========================================

    // GET /habits/templates -> Sistemdeki tüm şablonları getirir
    @GetMapping("/templates")
    public ResponseEntity<List<HabitTemplateDTO>> getAllTemplates() {
        return ResponseEntity.ok(habitService.getAllTemplates());
    }

    // GET /habits/templates/category/{category} -> Sadece "Sağlık" gibi belirli şablonları getirir
    @GetMapping("/templates/category/{category}")
    public ResponseEntity<List<HabitTemplateDTO>> getTemplatesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(habitService.getTemplatesByCategory(category));
    }


    // ==========================================
    // 2. KULLANICIYA ÖZEL İŞLEMLER (JWT GEREKTİRİR)
    // ==========================================

    // GET /habits -> Sadece token'ı gönderen kişinin kendi alışkanlıklarını getirir
    @GetMapping
    public ResponseEntity<List<UserHabitDTO>> getUserHabits(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String userEmail = userDetails.getUsername(); // Kimliği token'dan çektik
        return ResponseEntity.ok(habitService.getUserHabits(userEmail));
    }

    // POST /habits -> Kullanıcıya yeni bir alışkanlık oluşturur
    @PostMapping
    public ResponseEntity<UserHabitDTO> createUserHabit(
            @Valid @RequestBody UserHabitCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String userEmail = userDetails.getUsername(); // Kimliği token'dan çektik
        UserHabitDTO createdHabit = habitService.createUserHabit(userEmail, request);

        // Yeni bir kaynak oluşturulduğu için 201 CREATED dönüyoruz
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHabit);
    }
}