package ru.anton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.anton.Examiner2Application;
import ru.anton.entity.GazQuestions;
import ru.anton.repository.QuestionGazRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(QuestionGazRepo gazRepo) {
        return args -> {
            Examiner2Application obj = new Examiner2Application();
            InputStream inputStreamGazQuestion = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("Blog_7_1.txt");

            String[] arrayQuestion = getArray(inputStreamGazQuestion);

            for (String s : arrayQuestion) {

                List<String> list = new ArrayList<>(Arrays.asList(s.split("\\n")));

                log.info("Preload gaz question " + gazRepo.save(new GazQuestions(list.get(0),
                        list)));

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
                .split("\\n\\n");
    }
}
