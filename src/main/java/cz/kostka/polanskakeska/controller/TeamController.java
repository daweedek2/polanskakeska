package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.TeamFormDTO;
import cz.kostka.polanskakeska.entity.CrosswordPart;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.service.CacheService;
import cz.kostka.polanskakeska.service.CrosswordPartService;
import cz.kostka.polanskakeska.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/team")
public class TeamController {
    private final TeamService teamService;
    private final CrosswordPartService crosswordPartService;
    private final CacheService cacheService;

    @Autowired
    public TeamController(final TeamService teamService,
                          final CrosswordPartService crosswordPartService,
                          final CacheService cacheService) {
        this.teamService = teamService;
        this.crosswordPartService = crosswordPartService;
        this.cacheService = cacheService;
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

    @GetMapping("/{teamName}")
    public String getTeam(final @PathVariable String teamName, final Model model) {
        final Team team = teamService.getTeamByName(teamName).get();
        model.addAttribute("teamDetail", team);
        final List<CrosswordPart> crosswordParts = crosswordPartService.getAllByIds(team.getCrossword().getPartMap().values());
        model.addAttribute("crosswordParts", crosswordParts);
        return "admin-team-detail";
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

    @GetMapping("/solve")
    public String solveCacheForTeam(
            final @RequestParam("teamName") String teamName,
            final @RequestParam("cacheNumber") int cacheNumber) {

        teamService.addSolvedCache(teamService.getTeamByName(teamName).get(), cacheService.getCacheByNumber(cacheNumber).get());
        return "redirect:/admin/team/" + teamName;
    }
}
