package ru.anton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anton.entity.GazQuestions;

import java.util.List;

@Repository
public interface QuestionGazRepo extends JpaRepository<GazQuestions, Long> {
}
