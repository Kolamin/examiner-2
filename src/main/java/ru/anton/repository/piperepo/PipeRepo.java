package ru.anton.repository.piperepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.pipes.PipeQuestions;

public interface PipeRepo extends JpaRepository<PipeQuestions, Long> {
    PipeQuestions findById(long id);
}
