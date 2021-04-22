package ru.anton.repository.gazrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.gazentity.GazQuestions;

public interface QuestionGazRepo extends JpaRepository<GazQuestions, Long> {
    GazQuestions findById(long id);
}
