package ru.anton.repository.boilerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.boilerentity.BoilerQuestions;

public interface BoilerRepository extends JpaRepository<BoilerQuestions, Long> {
    BoilerQuestions findById(long id);
}
