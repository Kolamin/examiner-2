package ru.anton.repository.vesselrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anton.entity.vessel.VesselQuestions;

@Repository
public interface VesselRepo extends JpaRepository<VesselQuestions, Long> {
    VesselQuestions findById(long id);
}
