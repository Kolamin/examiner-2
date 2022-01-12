package ru.anton.entity.pipes;

import lombok.*;
import ru.anton.entity.AbstractIdentifiableObject;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "correct_pipe_answer")
public class CorrectPipeAnswer extends AbstractIdentifiableObject {
//    @Getter
//    @Setter
//    @NonNull
//    @Column(length = 4000)
//    private String nameAnswer;

    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    private String correctAnswer;
}
