package ru.anton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anton.entity.gazentity.GazQuestions;
import ru.anton.repository.gazrepo.CorectGazAnswerRepo;
import ru.anton.repository.gazrepo.QuestionGazRepo;
import ru.anton.repository.gazrepo.QuestionGazRepoNew;

@Controller
public class GazQuestionController {
    private final QuestionGazRepo questionGazRepo;
    private final CorectGazAnswerRepo answerRepository;
    private final QuestionGazRepoNew questionGazRepoNew;


    @Autowired
    public GazQuestionController(QuestionGazRepo questionGazRepo, CorectGazAnswerRepo answerRepository, QuestionGazRepoNew questionGazRepoNew) {
        this.questionGazRepo = questionGazRepo;
        this.answerRepository = answerRepository;
        this.questionGazRepoNew = questionGazRepoNew;
    }


    @GetMapping("")
    public String getHome() {
        return "home";
    }


    @GetMapping("/gazall")
    public String getAllGazQuestion(Model model) {
        model.addAttribute("gaz", questionGazRepo.findAll());
        return "gazquestions";
    }

    @GetMapping("/gazallnew")
    public String getAllNewGazQuestion(Model model) {
        model.addAttribute("gazNew", questionGazRepoNew.findAll());
        return "gazquestionsnew";
    }

    @GetMapping("/concretegaz/{id}")
    public String getSingleGazQuestion(@PathVariable("id") long id, Model model) {
        model.addAttribute("gazquestion", questionGazRepo.findById(id));
        return "gazquestion";
    }

    @GetMapping("/concretegaznew/{id}")
    public String getSingleGazQuestionNew(@PathVariable("id") long id, Model model) {
        String nameQuestionsNew = questionGazRepoNew.findById(id)
                .getName();
        long idNew = questionGazRepo.findIdByName(nameQuestionsNew)
                .getId();
        model.addAttribute("gazquestionNew", questionGazRepo.findById(idNew));
        model.addAttribute("id", id);
        return "gazquestionnew";
    }

    //Старые вопросы
    @PostMapping("/gazanswer/{id}")
    public String getGazAnswer(Model model, @ModelAttribute GazQuestions answer,
                               @PathVariable("id") long id) {
        model.addAttribute("answer", answer);
        model.addAttribute("correctAnswer", answerRepository.findById(id));
        model.addAttribute("id", id);
        return "answergaz";
    }

    //Новые вопросы
    @PostMapping("/gazanswernew/{curId}/{id}")
    public String getGazAnswerNew(Model model, @ModelAttribute GazQuestions answer,
                                  @PathVariable("id") long id, @PathVariable("curId") long curId) {
        model.addAttribute("answer", answer);
        model.addAttribute("correctAnswer", answerRepository.findById(id));

        model.addAttribute("currentId", curId);
        return "answergaznew";
    }

    @GetMapping("/answerall")
    public String getAllCorectAnswer(Model model) {
        model.addAttribute("gazcorectanswers", answerRepository.findAll());
        return "corectanswers";
    }
}
