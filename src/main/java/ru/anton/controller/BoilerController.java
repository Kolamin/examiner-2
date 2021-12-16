package ru.anton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anton.entity.boilerentity.BoilerQuestions;
import ru.anton.entity.boilerentity.ConcreteBoilerAnswer;
import ru.anton.repository.boilerrepo.BoilerRepository;
import ru.anton.repository.boilerrepo.CorrectAnswerBoilerRepo;


@Controller
@RequestMapping("boiler")
public class BoilerController {
    private final BoilerRepository boilerRepository;
    private final CorrectAnswerBoilerRepo correctAnswerBoilerRepo;

    public BoilerController(BoilerRepository boilerRepository, CorrectAnswerBoilerRepo correctAnswerBoilerRepo) {
        this.boilerRepository = boilerRepository;
        this.correctAnswerBoilerRepo = correctAnswerBoilerRepo;
    }


//    @GetMapping("/boilerall")
//    public String getBoilerAllQuestions(Model model){
//        model.addAttribute( "questions", boilerRepository.findAll() );
//        return "boilerall";
//    }

    @GetMapping("/concreteboiler/{id}")
    public String getSingleBoilerQuestion(Model model, @PathVariable("id") long id) {
        model.addAttribute("question", boilerRepository.findById(id));
        return "boilerQuestion";
    }

    @PostMapping("/answer/{id}")
    public String getAnswer(Model model, @ModelAttribute BoilerQuestions answer, @PathVariable("id") long id) {
        model.addAttribute("answer", answer);
        ConcreteBoilerAnswer answerBoilerRepoById = correctAnswerBoilerRepo.findById(id);

        StringBuilder resultAnswer = new StringBuilder();
        for (String s : answerBoilerRepoById.getCorrectAnswer()) {
            resultAnswer.append(s).append(",");
        }
        model.addAttribute("correctAnswer", resultAnswer.substring(0, resultAnswer.length() - 1));
        model.addAttribute("id", id);
        return "boilerAnswer";
    }
}
