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
@Table(name = "correct_gaz_answer")
public class CorectGazAnswer extends AbstractIdentifiableObject {

    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    private String correctAnswer;
}
