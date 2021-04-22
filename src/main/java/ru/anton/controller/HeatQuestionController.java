package ru.anton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anton.entity.heatentity.HeatQuestion;
import ru.anton.repository.heatrepo.CorectHeatAnswerRepo;
import ru.anton.repository.heatrepo.HeatQuestionRepo;

@Controller
@RequestMapping("/heat")
public class HeatQuestionController {

    private final HeatQuestionRepo heatQuestionRepo;

    private final CorectHeatAnswerRepo corectHeatAnswerRepo;

    public HeatQuestionController(HeatQuestionRepo heatQuestionRepo, CorectHeatAnswerRepo corectHeatAnswerRepo) {
        this.heatQuestionRepo = heatQuestionRepo;
        this.corectHeatAnswerRepo = corectHeatAnswerRepo;
    }

    @GetMapping("/heatall")
    public String getAllHeatQuestion(Model model){
        model.addAttribute("heatQuestions", heatQuestionRepo.findAll());
        return "heatall";
    }

    @GetMapping("/concrete/{id}")
    public String getHeatQuestion(@PathVariable("id") long id,
                                  Model model){
        model.addAttribute("question", heatQuestionRepo.findById(id));
        return "heatQuestion";
    }

    @PostMapping("/answer/{id}")
    public String getAnswer(Model model, @ModelAttribute HeatQuestion answer, @PathVariable("id") long id){
        model.addAttribute("answers",  answer);
        model.addAttribute("correctAnswer", corectHeatAnswerRepo.findById(id));
        model.addAttribute("id", id);
        return "heatAnswer";
    }
}
