package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team save(final Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> getTeamByName(final String teamName) {
        if (teamName == null) {
            return Optional.empty();
        }

        return teamRepository.findTeamByName(teamName);
    }
}
