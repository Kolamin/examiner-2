package ru.anton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.GazQuestions;

import java.util.Optional;

public interface QuestionGazRepo extends JpaRepository<GazQuestions, Long> {
    public GazQuestions findById(long id);
}
