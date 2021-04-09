package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.TeamFormDTO;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public String teamOverview(final Model model) {
        model.addAttribute("allTeams", teamService.getAllTeams());
        model.addAttribute("teamFormDTO", new TeamFormDTO());

        return "admin-team";
    }

    @GetMapping("/getAll")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/get/{teamName}")
    public Team getTeam(final @PathVariable String teamName) {
        return teamService.getTeamByName(teamName).get();
    }

    @PostMapping("/save")
    public String saveTeam(final @ModelAttribute TeamFormDTO teamFormDTO, final RedirectAttributes redirectAttributes) {
        final Team team = teamService.saveDTO(teamFormDTO);

        if (team != null) {
            redirectAttributes.addFlashAttribute("teamCreated", true);
        } else {
            redirectAttributes.addFlashAttribute("teamCreated", false);
        }

        return "redirect:/admin/team";
    }
}
