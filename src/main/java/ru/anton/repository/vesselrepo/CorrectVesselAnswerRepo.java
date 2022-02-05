package ru.anton.repository.vesselrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anton.entity.vessel.CorrectVesselAnswer;

@Repository
public interface CorrectVesselAnswerRepo extends JpaRepository<CorrectVesselAnswer, Long> {
    CorrectVesselAnswer findById(long id);
}
