package ru.anton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anton.entity.pipes.PipeQuestions;
import ru.anton.entity.vessel.VesselQuestions;
import ru.anton.repository.vesselrepo.CorrectVesselAnswerRepo;
import ru.anton.repository.vesselrepo.VesselRepo;


@Controller
@RequestMapping("vessel")
public class VesselController {
    private final VesselRepo vesselRepo;
    private final CorrectVesselAnswerRepo vesselAnswerRepo;

    @Autowired
    public VesselController(VesselRepo vesselRepo, CorrectVesselAnswerRepo vesselAnswerRepo) {
        this.vesselRepo = vesselRepo;
        this.vesselAnswerRepo = vesselAnswerRepo;
    }

    @GetMapping("vesselall")
    public String getAllVesselQuestions(Model model) {
        model.addAttribute( "questions", vesselRepo.findAll() );
        return "vesselall";
    }

    //========================================================================================

    @GetMapping("/concrete/{id}")
    public String getVesselQuestion(@PathVariable("id") long id,
                                    Model model) {
        model.addAttribute( "question", vesselRepo.findById( id ) );
        return "vesselQuestion";
    }

    @PostMapping("/answer/{id}")
    public String getVesselAnswer(Model model,
                                  @ModelAttribute VesselQuestions answer, @PathVariable("id") long id) {
        model.addAttribute( "answer", answer );
        model.addAttribute( "correctAnswer", vesselAnswerRepo.findById( id ) );
        model.addAttribute( "id", id );
        return "vesselAnswer";
    }
}
