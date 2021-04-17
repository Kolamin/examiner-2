package ru.anton.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @NonNull
    @Column(length = 4000)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> testOptions;
}
