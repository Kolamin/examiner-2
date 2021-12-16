package ru.anton.repository.boilerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.boilerentity.ConcreteBoilerAnswer;

public interface CorrectAnswerBoilerRepo extends JpaRepository<ConcreteBoilerAnswer, Long> {
    ConcreteBoilerAnswer findById(long id);
}
