package HabitAppBackend.demo.repositories;

import HabitAppBackend.demo.domain.entities.EmotionLog;
import HabitAppBackend.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmotionLogRepository extends JpaRepository<EmotionLog, Long> {
    // Upsert (Var mı yok mu?) kontrolü için gereken sihirli metot
    Optional<EmotionLog> findByUserAndLogDate(User user, LocalDate logDate);
}