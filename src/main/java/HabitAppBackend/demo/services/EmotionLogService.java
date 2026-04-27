package HabitAppBackend.demo.services;

import HabitAppBackend.demo.domain.dto.EmotionLogDTO;
import HabitAppBackend.demo.domain.dto.EmotionLogRequest;

public interface EmotionLogService {
    // Upsert (Varsa güncelle, yoksa kaydet) işlemimiz
    EmotionLogDTO saveOrUpdateEmotionLog(String userEmail, EmotionLogRequest request);
}