package ru.anton.repository.gazrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.gazentity.GazQuestions;
import ru.anton.entity.gazentity.GazQuestionsNew;

public interface QuestionGazRepoNew extends JpaRepository<GazQuestionsNew, Long> {
    GazQuestionsNew findById(long id);
}
