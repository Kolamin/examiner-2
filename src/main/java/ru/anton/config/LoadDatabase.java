package ru.anton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.anton.Examiner2Application;
import ru.anton.entity.gazentity.CorectGazAnswer;
import ru.anton.entity.gazentity.GazQuestions;
import ru.anton.entity.gazentity.GazQuestionsNew;
import ru.anton.entity.heatentity.CorectHeatAnswer;
import ru.anton.entity.heatentity.HeatQuestion;
import ru.anton.entity.industrial.CorrectIndustAnswer;
import ru.anton.entity.industrial.IndustrialQuestions;
import ru.anton.repository.gazrepo.CorectGazAnswerRepo;
import ru.anton.repository.gazrepo.QuestionGazRepo;
import ru.anton.repository.gazrepo.QuestionGazRepoNew;
import ru.anton.repository.heatrepo.CorectHeatAnswerRepo;
import ru.anton.repository.heatrepo.HeatQuestionRepo;
import ru.anton.repository.industrepo.CorrectAnswerIndustRepository;
import ru.anton.repository.industrepo.IndustrialRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(QuestionGazRepo gazRepo,
                                   QuestionGazRepoNew gazNewRepo,
                                   CorectGazAnswerRepo corectGazAnswerRepo,
                                   HeatQuestionRepo heatQuestionRepo,
                                   CorectHeatAnswerRepo corectHeatAnswerRepo,
                                   IndustrialRepository indRepo,
                                   CorrectAnswerIndustRepository correctAnswerIndustRepository) {
        return args -> {
            Examiner2Application obj = new Examiner2Application();
            //-----------------------------------------------------------
            InputStream inputStreamGazQuestion = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/Blog_7_1.txt");

            InputStream inputStreamGazAnswer = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/Gaz_Answers.txt");
            //------------------------------------------------------------
            InputStream inputStreamHeatQuestion = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/Thermal_power_plants.txt");

            InputStream inputStreamHeatAnswer = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/AnswerHeat.txt");
            //----------------------------------------------------------

            //----------------------------------------------------------
            //New questions for gaz
            InputStream inputStreamGazQuestionNew = obj.getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/Blog_7_1_new.txt");
            //-----------------------------------------------------------

            //-----------------------------------------------------------
            InputStream inputIndustrialQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/A_1_tests.txt");

            InputStream inputIndustAnswer = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/A_1_answer.txt");

            preloadDatabaseForAllTests(gazRepo,
                    gazNewRepo,
                    corectGazAnswerRepo,
                    heatQuestionRepo,
                    corectHeatAnswerRepo,
                    indRepo,
                    correctAnswerIndustRepository,
                    inputStreamGazQuestion,
                    inputStreamGazAnswer,
                    inputStreamHeatQuestion,
                    inputStreamHeatAnswer,
                    inputStreamGazQuestionNew,
                    inputIndustrialQuestions,
                    inputIndustAnswer);

        };
    }

    private void preloadDatabaseForAllTests(QuestionGazRepo gazRepo,
                                            QuestionGazRepoNew gazNewRepo,
                                            CorectGazAnswerRepo corectGazAnswerRepo,
                                            HeatQuestionRepo heatQuestionRepo,
                                            CorectHeatAnswerRepo corectHeatAnswerRepo,
                                            IndustrialRepository indRepo,
                                            CorrectAnswerIndustRepository correctAnswerIndustRepository,
                                            InputStream inputStreamGazQuestion,
                                            InputStream inputStreamGazAnswer,
                                            InputStream inputStreamHeatQuestion,
                                            InputStream inputStreamHeatAnswer,
                                            InputStream inputStreamGazQuestionNew,
                                            InputStream inputIndustrialQuestions,
                                            InputStream inputIndustAnswer) throws IOException {
        //Preload new questions for gaz and test options
        String[] tempNew = getArray(inputStreamGazQuestionNew);
        String[] arrayQuestionNew = Arrays.copyOfRange(tempNew, 1, tempNew.length);
        for (String s : arrayQuestionNew) {
            String[] split = s.split("\\n");
            int length = split.length;
            log.info("Preload gaz database new" + gazNewRepo.save(new GazQuestionsNew(split[0])));
        }
        //-----------------------------------------------------------------------

        //Preload questions for gaz and test options
        String[] temp = getArray(inputStreamGazQuestion);
        String[] arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);
        for (String s : arrayQuestion) {
            String[] split = s.split("\\n");
            int length = split.length;
            log.info("Preload gaz database " + gazRepo.save(new GazQuestions(split[0], Arrays.asList(Arrays.copyOfRange(split, 1, length)))));
        }
        //-----------------------------------------------------------------------

        //Preload corect gaz answers
        temp = getArray(inputStreamGazAnswer);
        String[] arrayAnswers = Arrays.copyOfRange(temp, 1, temp.length);
        for (String s : arrayAnswers) {
            String[] split = s.split("\\n");
            int length = split.length;
            log.info("Preload gaz answer " + corectGazAnswerRepo.save(length == 2 ? new CorectGazAnswer(split[1].trim()) : new CorectGazAnswer(split[1].trim() + ",\t" + split[2].trim())));
        }
        //------------------------------------------------------------------------

        //Preload question for heat and test options
        temp = getArray(inputStreamHeatQuestion);
        String[] arrayHeatQuestion = Arrays.copyOfRange(temp, 1, temp.length);
        for (String s : arrayHeatQuestion) {
            String[] split = s.split("\\n");
            log.info("Preload heat database " + heatQuestionRepo.save(new HeatQuestion(split[1], Arrays.asList(Arrays.copyOfRange(split, 2, split.length)))));
        }
        //--------------------------------------------------------------------------

        //Preload correct answer for heat
        temp = getArray(inputStreamHeatAnswer);
        String[] arrayCorrectAnswer = Arrays.copyOfRange(temp, 1, temp.length);
        for (String s : arrayCorrectAnswer) {
            String[] split = s.split("\\n");
            log.info("Preload heat answer " + corectHeatAnswerRepo.save(new CorectHeatAnswer(split[2])));
        }

        //Preload for indust
        temp = getArray(inputIndustrialQuestions);
        String[] arrayIndustQuestion = Arrays.copyOfRange(temp, 1, temp.length);

        for (String s : arrayIndustQuestion) {
            String[] split = s.split("\\n");
            int length = split.length;
            log.info("Preload industrial question database " + indRepo
                    .save(new IndustrialQuestions(split[0],
                            Arrays.asList(Arrays.copyOfRange(split, 1, length)))));
        }

        temp = getArray(inputIndustAnswer);
        arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);
        for (String s : arrayQuestion) {
            String[] split = s.split("\\n");
            log.info("Preload answer for industrial" + correctAnswerIndustRepository
                    .save(new CorrectIndustAnswer(split[0] ,Arrays
                            .asList(Arrays.
                                    copyOfRange(split, 1, split.length)))));
        }
    }

    private String[] getArray(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            content = new StringBuilder();

            String st;

            while ((st = br.readLine()) != null) {
                if (st.contains("Билет"))
                    continue;
                if (st.contains("Правила по охране труда при эксплуатации тепловых энергоустановок"))
                    continue;
                if (st.contains("Мероприятия по оказани первой помощи (Приказ Минздрава России от 04.05.2012 № 477н)"))
                    continue;
                content.append(st)
                        .append("\n");
            }
        }
        return content.toString()
                .split("(\\d+\\.\\s)|(Вопрос \\d+)");
    }
}
