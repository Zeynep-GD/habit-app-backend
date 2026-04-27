package HabitAppBackend.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(
        name = "emotion_logs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "log_date"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "log_date", nullable = false)
    private LocalDate logDate;

    private Integer joyRate = 0;       // Neşe
    private Integer sadnessRate = 0;   // Hüzün
    private Integer angerRate = 0;     // Öfke
    private Integer fearRate = 0;      // Korku
    private Integer disgustRate = 0;   // Tiksinti
    private Integer anxietyRate = 0;       // Kaygı
    private Integer embarrassmentRate = 0; // Utanç
    private Integer envyRate = 0;          // Kıskançlık / İmrenme
    private Integer ennuiRate = 0;         // Bıkkınlık / Can Sıkıntısı
}