package ru.anton.repository.oilrepo;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.oilentity.OilQuestions;

import java.util.Optional;

public interface OilAllQuestRepo extends JpaRepository<OilQuestions, Long> {
    Optional<OilQuestions> findById(Long id);
}
