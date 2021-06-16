package ru.anton.entity.gazentity;

import lombok.*;
import ru.anton.entity.AbstractIdentifiableObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "gaz_questions_new")
public class GazQuestionsNew extends AbstractIdentifiableObject {

    @Getter
    @Setter
    @Column(length = 4000)
    @NonNull
    private String name;
}
