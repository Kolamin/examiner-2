package ru.anton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anton.entity.oilentity.CorrectOilAnswer;
import ru.anton.entity.oilentity.OilQuestions;
import ru.anton.repository.oilrepo.CorrectAnswerOilRepo;
import ru.anton.repository.oilrepo.OilAllQuestRepo;


@Controller
@RequestMapping("oil")
public class OilController {

    private final OilAllQuestRepo oilAllQuestRepo;
    private final CorrectAnswerOilRepo correctAnswerOilRepo;

    public OilController(OilAllQuestRepo oilAllQuestRepo, CorrectAnswerOilRepo correctAnswerOilRepo) {
        this.oilAllQuestRepo = oilAllQuestRepo;
        this.correctAnswerOilRepo = correctAnswerOilRepo;
    }

    @GetMapping("/concreteoil/{id}")
    public String getSingleOilQuestion(Model model, @PathVariable("id") Long id){
        model.addAttribute("oilQuestion", oilAllQuestRepo.findById(id).get());
        return "oilquestion";
    }

    @PostMapping("/answer/{id}")
    public String getOilAnswer(Model model, @ModelAttribute OilQuestions answer, @PathVariable("id") Long id){
        model.addAttribute("answer", answer);
        CorrectOilAnswer answerOilRepositoryById = correctAnswerOilRepo.findById(id).get();

        StringBuilder resultOilAnswer = new StringBuilder();
        for (String s : answerOilRepositoryById.getCorrectAnswer()) {
            resultOilAnswer.append(s).append(",");
        }
        model.addAttribute("correctAnswer", resultOilAnswer.substring(0, resultOilAnswer.length() - 1));
        model.addAttribute("id", id);
        return "oilAnswer";
    }
}
