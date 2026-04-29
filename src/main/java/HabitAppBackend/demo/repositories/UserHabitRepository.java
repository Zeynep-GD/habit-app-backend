package HabitAppBackend.demo.repositories;

import HabitAppBackend.demo.domain.entities.User;
import HabitAppBackend.demo.domain.entities.UserHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHabitRepository extends JpaRepository<UserHabit, Long> {
    // Bir kullanıcının profiline girdiğinde sadece KENDİ alışkanlıklarını
    // görebilmesi için gereken o sihirli metot.
    List<UserHabit> findByUser(User user);
}