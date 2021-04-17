package ru.anton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.anton.repository.QuestionGazRepo;

@Controller
public class GazQuestionController {
    private final QuestionGazRepo questionGazRepo;

    @Autowired
    public GazQuestionController(QuestionGazRepo questionGazRepo) {
        this.questionGazRepo = questionGazRepo;
    }

    @GetMapping("/gazall")
    public String getAllGazQuestion(Model model){
        model.addAttribute("gaz", questionGazRepo.findAll());
        return "gazquestion";
    }
}
