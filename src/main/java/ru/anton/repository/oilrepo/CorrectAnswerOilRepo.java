package ru.anton.repository.oilrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.oilentity.CorrectOilAnswer;

import java.util.Optional;

public interface CorrectAnswerOilRepo extends JpaRepository<CorrectOilAnswer, Long> {

    Optional<CorrectOilAnswer> findById(Long id);
}
