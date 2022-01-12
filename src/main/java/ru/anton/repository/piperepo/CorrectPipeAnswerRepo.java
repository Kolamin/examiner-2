package ru.anton.repository.piperepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.pipes.CorrectPipeAnswer;

public interface CorrectPipeAnswerRepo extends JpaRepository<CorrectPipeAnswer, Long> {
    CorrectPipeAnswer findById(long id);
}
