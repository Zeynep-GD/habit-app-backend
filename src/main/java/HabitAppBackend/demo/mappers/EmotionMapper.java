package HabitAppBackend.demo.mappers;

import HabitAppBackend.demo.domain.dto.EmotionLogDTO;
import HabitAppBackend.demo.domain.entities.EmotionLog;

public class EmotionMapper {

    // Entity'den -> DTO'ya (Dışarıya veri çıkarken)
    public static EmotionLogDTO toDto(EmotionLog log) {
        return new EmotionLogDTO(
                log.getId(),
                log.getLogDate(),
                log.getJoyRate(),
                log.getSadnessRate(),
                log.getAngerRate(),
                log.getFearRate(),
                log.getDisgustRate(),
                log.getAnxietyRate(),
                log.getEmbarrassmentRate(),
                log.getEnvyRate(),
                log.getEnnuiRate()
        );
    }
}