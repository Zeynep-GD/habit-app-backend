package HabitAppBackend.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_habits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bu alışkanlık kime ait?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name; // Kullanıcı isterse şablondaki adı değiştirebilir (Örn: "Günde 3 Litre Su")

    private String category;

    @Column(name = "icon_name")
    private String iconName;

    // --- OYUNLAŞTIRMA (GAMIFICATION) KISMI ---

    @Column(name = "current_streak")
    private Integer currentStreak = 0; // Şu anki aktif serisi (Kaç gündür bozmadan yapıyor?)

    @Column(name = "longest_streak")
    private Integer longestStreak = 0; // En uzun serisi (Rekoru)

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}