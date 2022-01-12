package ru.anton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anton.entity.pipes.PipeQuestions;
import ru.anton.repository.piperepo.CorrectPipeAnswerRepo;
import ru.anton.repository.piperepo.PipeRepo;

@Controller
@RequestMapping("pipe")
public class PipeController {
    private final PipeRepo pipeRepo;
    private final CorrectPipeAnswerRepo pipeAnswerRepo;

    public PipeController(PipeRepo pipeRepo, CorrectPipeAnswerRepo pipeAnswerRepo) {
        this.pipeRepo = pipeRepo;
        this.pipeAnswerRepo = pipeAnswerRepo;
    }

//===========================================================================================
    @GetMapping("/concrete/{id}")
    public String getPipeQuestion(@PathVariable("id") long id,
                                  Model model) {
        model.addAttribute("question", pipeRepo.findById(id));
        return "pipeQuestion";
    }

    @PostMapping("/answer/{id}")
    public String getPipeAnswer(Model model,
                            @ModelAttribute PipeQuestions answer, @PathVariable("id") long id) {
        model.addAttribute("answer", answer);
        model.addAttribute("correctAnswer", pipeAnswerRepo.findById(id));
        model.addAttribute("id", id);
        return "pipeAnswer";
    }
}
