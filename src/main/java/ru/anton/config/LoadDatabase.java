package ru.anton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.anton.Examiner2Application;
import ru.anton.entity.CorectGazAnswer;
import ru.anton.entity.GazQuestions;
import ru.anton.repository.CorectGazAnswerRepo;
import ru.anton.repository.QuestionGazRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(QuestionGazRepo gazRepo, CorectGazAnswerRepo corectGazAnswerRepo) {
        return args -> {
            Examiner2Application obj = new Examiner2Application();
            InputStream inputStreamGazQuestion = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("files/Blog_7_1.txt");

            InputStream inputStreamGazAnswer = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("files/Gaz_Answers.txt");

            //Preload questions and test options
            String[] temp = getArray(inputStreamGazQuestion);
            String[] arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);

            for (String s : arrayQuestion) {
                String[] split = s.split("\\n");
                int length = split.length;
                log.info("Preload database " + gazRepo.save(new GazQuestions(split[0],
                        Arrays.asList(Arrays.copyOfRange(split, 1, length)))));
            }
            //-----------------------------------------------------------------------

            //Preload corect answers
            temp = getArray(inputStreamGazAnswer);
            String[] arrayAnswers = Arrays.copyOfRange(temp, 1, temp.length);
//            for (String s : temp) {
//                String[] split = s.split("\\n");
//                System.out.println(s);
//                System.out.println(split.length);
//            }
            for (String s : arrayAnswers) {
                String[] split = s.split("\\n");
                int length = split.length;
                log.info("Preload answer " + corectGazAnswerRepo.save(new CorectGazAnswer(split[1])));
            }

        };
    }

    private String[] getArray(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            content = new StringBuilder();

            String st;

            while ((st = br.readLine()) != null) {
                if (st.contains("Билет"))
                    continue;
                content.append(st)
                        .append("\n");
            }
        }
        return content.toString()
                .split("\\d+\\.\\s");
    }
}
