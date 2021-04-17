package ru.anton.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "gaz_questions")
public class GazQuestions extends AbstractIdentifiableObject{

    @Getter
    @Setter
    @Column(length = 4000)
    @NonNull
    private String name;

}
