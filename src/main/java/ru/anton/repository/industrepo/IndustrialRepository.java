package ru.anton.repository.industrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.industrial.IndustrialQuestions;

public interface IndustrialRepository
        extends JpaRepository<IndustrialQuestions, Long> {
    IndustrialQuestions findById(long id);
}
