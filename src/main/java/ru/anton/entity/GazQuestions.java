package ru.anton.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "gaz_questions")
public class GazQuestions extends AbstractIdentifiableObject{

    @Getter
    @Setter
    @Column(length = 4000)
    @NonNull
    private String name;

    @Getter
    @Setter
    private String answer;

    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> testOptions;
}
