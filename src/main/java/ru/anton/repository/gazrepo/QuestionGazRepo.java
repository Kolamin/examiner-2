package ru.anton.repository.gazrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.anton.entity.gazentity.GazQuestions;

public interface QuestionGazRepo extends JpaRepository<GazQuestions, Long> {
    GazQuestions findById(long id);

    @Query("select q from GazQuestions q where q.name = :name")
    GazQuestions findIdByName(@Param("name") String name);
}
