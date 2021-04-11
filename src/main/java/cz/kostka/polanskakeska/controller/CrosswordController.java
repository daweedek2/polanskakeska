package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.CrosswordCheckDTO;
import cz.kostka.polanskakeska.dto.CrosswordDetailDTO;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.service.CrosswordService;
import cz.kostka.polanskakeska.service.TeamService;
import cz.kostka.polanskakeska.service.ValidationService;
import cz.kostka.polanskakeska.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/crossword")
public class CrosswordController {
    private final ValidationService validationService;
    private final CrosswordService crosswordService;

    @Autowired
    public CrosswordController(final ValidationService validationService,
                               final CrosswordService crosswordService) {
        this.validationService = validationService;
        this.crosswordService = crosswordService;
    }

    @GetMapping
    public String viewForm(final Model model) {
        model.addAttribute("form", new CrosswordCheckDTO());
        return "crossword";
    }

    @PostMapping("/verify")
    public String viewCrosswordCheck(final @ModelAttribute CrosswordCheckDTO dto,
                                     final RedirectAttributes redirectAttributes) {

        Team team = validationService.validateCrosswordRequest(dto);

        if (team != null) {
            final CrosswordDetailDTO crosswordDTO = crosswordService.buildDetail(team.getCrossword());
            redirectAttributes.addFlashAttribute("crossword", crosswordDTO);
        } else {
            redirectAttributes.addFlashAttribute("result",
                    ResultUtil.createFailedResult("Ověření nebylo úspěšné. V případě problémů prosím kontaktujte pořadatele."));
        }
        return "redirect:/crossword";
    }
}
