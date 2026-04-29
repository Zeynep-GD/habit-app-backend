package HabitAppBackend.demo.services.impl;

import HabitAppBackend.demo.domain.dto.EmotionLogDTO;
import HabitAppBackend.demo.domain.dto.EmotionLogRequest;
import HabitAppBackend.demo.domain.entities.EmotionLog;
import HabitAppBackend.demo.domain.entities.User;
import HabitAppBackend.demo.exception.ResourceNotFoundException;
import HabitAppBackend.demo.mappers.EmotionMapper;
import HabitAppBackend.demo.repositories.EmotionLogRepository;
import HabitAppBackend.demo.repositories.UserRepository;
import HabitAppBackend.demo.services.EmotionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionLogServiceImpl implements EmotionLogService {

    private final EmotionLogRepository emotionLogRepository;
    private final UserRepository userRepository;

    @Override
    public EmotionLogDTO saveOrUpdateEmotionLog(String userEmail, EmotionLogRequest request) {

        //Kullanıcıyı bul
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));

        //MATEMATİK KONTROLÜ
        int totalEmotionRate = request.joyRate() + request.sadnessRate() + request.angerRate() +
                request.fearRate() + request.disgustRate() + request.anxietyRate() +
                request.embarrassmentRate() + request.envyRate() + request.ennuiRate();

        if (totalEmotionRate > 100) {
            // GlobalExceptionHandler bunu otomatik yakalayıp Flutter'a 400 dönecek
            throw new IllegalArgumentException("Duygu yüzdelerinin toplamı 100'ü geçemez! Şu anki toplam: " + totalEmotionRate);
        }

        // UPSERT MANTIĞI: Veritabanında bu kullanıcının, bu tarihte bir kaydı var mı?
        EmotionLog emotionLog = emotionLogRepository.findByUserAndLogDate(user, request.logDate())
                .orElse(new EmotionLog()); // Bulamazsa yepyeni ve boş bir EmotionLog nesnesi yaratır

        // Eğer bu yeni bir kayıtsa (id'si yoksa), sahibi ve tarihi set et
        if (emotionLog.getId() == null) {
            emotionLog.setUser(user);
            emotionLog.setLogDate(request.logDate());
        }

        // İçeri giren verileri nesnemize dolduruyoruz (Eski kayıt bile olsa değerleri ezerek güncelleriz)
        emotionLog.setJoyRate(request.joyRate());
        emotionLog.setSadnessRate(request.sadnessRate());
        emotionLog.setAngerRate(request.angerRate());
        emotionLog.setFearRate(request.fearRate());
        emotionLog.setDisgustRate(request.disgustRate());
        emotionLog.setAnxietyRate(request.anxietyRate());
        emotionLog.setEmbarrassmentRate(request.embarrassmentRate());
        emotionLog.setEnvyRate(request.envyRate());
        emotionLog.setEnnuiRate(request.ennuiRate());

        // Kaydetmede eğer nesnede ID varsa Update atar, ID yoksa Insert atar.
        EmotionLog savedLog = emotionLogRepository.save(emotionLog);

        return EmotionMapper.toDto(savedLog);
    }
}