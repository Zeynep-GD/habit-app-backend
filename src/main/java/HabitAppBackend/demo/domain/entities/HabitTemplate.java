package HabitAppBackend.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habit_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Örn: "Su İçmek", "Meditasyon"

    @Column(nullable = false)
    private String category; // Örn: "Sağlık", "Odaklanma", "Beslenme"

    private String description; // Örn: "Günde en az 2 litre su içerek vücudunu zinde tut."

    @Column(name = "icon_name")
    private String iconName; // İleride Flutter tarafında ikon göstermek için (Örn: "water_drop_icon")
}