package ru.anton.repository.heatrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.heatentity.HeatQuestion;

public interface HeatQuestionRepo extends JpaRepository<HeatQuestion, Long> {
    HeatQuestion findById(long id);
}
