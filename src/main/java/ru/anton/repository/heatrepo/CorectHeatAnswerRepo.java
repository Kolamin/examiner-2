package ru.anton.repository.heatrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.heatentity.CorectHeatAnswer;
import ru.anton.entity.heatentity.HeatQuestion;

public interface CorectHeatAnswerRepo extends JpaRepository<CorectHeatAnswer, Long> {
    CorectHeatAnswer findById(long id);
}
