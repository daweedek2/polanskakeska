package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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

    public void addSolvedCache(final Team team, final Cache cache) {
        final LocalDateTime currentTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()),
                TimeZone.getDefault().toZoneId());
        team.getSolvedCaches().add(cache);
        team.getSolvedCachesTimestamps().putIfAbsent(String.valueOf(cache.getNumber()), currentTime);
        this.save(team);
    }
}
