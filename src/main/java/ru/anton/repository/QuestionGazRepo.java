package ru.anton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.GazQuestions;

public interface QuestionGazRepo extends JpaRepository<GazQuestions, Long> {
}
