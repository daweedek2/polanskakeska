package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
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
    public Team saveTeam(final @RequestBody Team team) {
        return teamService.save(team);
    }
}
