package ru.anton.repository.industrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.industrial.CorrectIndustAnswer;

public interface CorrectAnswerIndustRepository extends JpaRepository<CorrectIndustAnswer, Long> {
    CorrectIndustAnswer findById(long id);
}
