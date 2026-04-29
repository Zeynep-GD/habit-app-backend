package HabitAppBackend.demo.repositories;

import HabitAppBackend.demo.domain.entities.HabitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitTemplateRepository extends JpaRepository<HabitTemplate, Long> {
    // İleride Flutter'da "Sadece 'Sağlık' kategorisindeki şablonları göster"
    // demek istersek diye bu şık metodu baştan ekliyoruz.
    List<HabitTemplate> findByCategory(String category);
}