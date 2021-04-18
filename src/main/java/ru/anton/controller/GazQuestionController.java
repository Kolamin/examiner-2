package ru.anton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anton.entity.GazQuestions;
import ru.anton.repository.CorectGazAnswerRepo;
import ru.anton.repository.QuestionGazRepo;

@Controller
public class GazQuestionController {
    private final QuestionGazRepo questionGazRepo;

    private final CorectGazAnswerRepo answerRepository;


    @Autowired
    public GazQuestionController(QuestionGazRepo questionGazRepo, CorectGazAnswerRepo answerRepository) {
        this.questionGazRepo = questionGazRepo;
        this.answerRepository = answerRepository;
    }


    @GetMapping("/gazall")
    public String getAllGazQuestion(Model model){
        model.addAttribute("gaz", questionGazRepo.findAll());
        return "gazquestions";
    }

    @GetMapping("/concrete/{id}")
    public String getSingleGazQuestion(@PathVariable("id") Long id, Model model){
        model.addAttribute("gazquestion", questionGazRepo.findById(id));
        return "gazquestion";
    }

    @PostMapping("/answer/{id}")
    public String getGazAnswer(Model model, @ModelAttribute GazQuestions answer,
                               @PathVariable("id") Long id){
        model.addAttribute("answers",  answer);
        model.addAttribute("correctAnswer", answerRepository.findById(id));
        model.addAttribute("id", id);
        return "answer";
    }

    @GetMapping("/answerall")
    public String getAllCorectAnswer(Model model){
        model.addAttribute("gazcorectanswers", answerRepository.findAll());
        return "corectanswers";
    }
}
