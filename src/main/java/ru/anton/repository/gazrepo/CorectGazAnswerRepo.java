package ru.anton.repository.gazrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.gazentity.CorectGazAnswer;

public interface CorectGazAnswerRepo extends JpaRepository<CorectGazAnswer, Long> {
    CorectGazAnswer findById(long id);
}
