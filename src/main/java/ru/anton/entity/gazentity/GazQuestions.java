package ru.anton.entity.gazentity;

import lombok.*;
import ru.anton.entity.AbstractIdentifiableObject;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "gaz_questions")
public class GazQuestions extends AbstractIdentifiableObject {
    @Getter
    @Setter
    @Column(length = 4000)
    @NonNull
    private String name;

    private String answer;
    public String getAnswer() {return answer == null ? answer = "выберите ответ!!!" : answer;}
    public void setAnswer(String answer) {this.answer = answer;}

    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> testOptions;
}
