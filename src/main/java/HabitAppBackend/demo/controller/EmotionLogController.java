package HabitAppBackend.demo.controller;

import HabitAppBackend.demo.domain.dto.EmotionLogDTO;
import HabitAppBackend.demo.domain.dto.EmotionLogRequest;
import HabitAppBackend.demo.services.EmotionLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emotions")
@RequiredArgsConstructor
public class EmotionLogController {

    private final EmotionLogService emotionLogService;

    // POST /emotions
    @PostMapping
    public ResponseEntity<EmotionLogDTO> saveOrUpdateEmotion(
            @Valid @RequestBody EmotionLogRequest request,
            // İşte o havalı kısım! Spring, JWT'den kullanıcının kim olduğunu buraya otomatik koyar.
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // VIP kartın (Token) içinden kullanıcının e-postasını çekiyoruz
        String userEmail = userDetails.getUsername();

        // Beynimize (Service) kullanıcının e-postasını ve isteğini yolluyoruz
        EmotionLogDTO response = emotionLogService.saveOrUpdateEmotionLog(userEmail, request);

        // İşlem başarılı, 200 OK ile kaydettiğimiz verinin son halini dönüyoruz
        return ResponseEntity.ok(response);
    }
}