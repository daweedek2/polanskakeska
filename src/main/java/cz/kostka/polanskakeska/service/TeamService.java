package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.dto.TeamFormDTO;
import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.entity.Crossword;
import cz.kostka.polanskakeska.entity.CrosswordPart;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final CrosswordService crosswordService;
    private final TeamRepository teamRepository;
    private final CrosswordPartService crosswordPartService;

    @Autowired
    public TeamService(final CrosswordService crosswordService,
                       final TeamRepository teamRepository,
                       final CrosswordPartService crosswordPartService) {
        this.crosswordService = crosswordService;
        this.teamRepository = teamRepository;
        this.crosswordPartService = crosswordPartService;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team save(final Team team) {
        if (team.getCrosswordId() == null) {
            team.setCrosswordId(crosswordService.createEmptyCrossword().getId());
        }
        return teamRepository.save(team);
    }

    public Optional<Team> getTeamByName(final String teamName) {
        if (teamName == null) {
            return Optional.empty();
        }

        return teamRepository.findTeamByName(teamName);
    }

    public void addSolvedCache(final Team team, final Cache cache) {
        final LocalDateTime currentTime = getCurrentTime();
        team.getSolvedCaches().add(cache);
        team.getSolvedCachesTimestamps().putIfAbsent(String.valueOf(cache.getNumber()), currentTime);
        updateTeamCrossword(team, cache);
        this.save(team);
    }

    private void updateTeamCrossword(final Team team, final Cache cache) {
        Crossword currentCrossword;
        if (team.getCrosswordId() == null || crosswordService.getById(team.getCrosswordId()) == null) {
            currentCrossword = crosswordService.createEmptyCrossword();
        } else {
            currentCrossword = crosswordService.getById(team.getCrosswordId());
        }

        final CrosswordPart partWithCode = crosswordPartService.getPartsOfCache(cache.getNumber()).stream()
                .filter(crosswordPart -> crosswordPart.getCode().equals(cache.getCode()))
                .findAny().orElseThrow();

        currentCrossword.getPartMap().replace(cache.getNumber(), partWithCode.getId());

        crosswordService.save(currentCrossword);
        team.setCrosswordId(currentCrossword.getId());
        this.save(team);
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()),
                TimeZone.getDefault().toZoneId());
    }

    public Team saveDTO(final TeamFormDTO teamFormDTO) {
        if (!isUniqueTeamName(teamFormDTO.getName()) || !isUniqueEmail(teamFormDTO.getEmail())) {
            return null;
        }

        Team team = new Team();
        team.setEmail(teamFormDTO.getEmail());
        team.setName(teamFormDTO.getName());

        Set<String> members = parseMembers(teamFormDTO.getMembers());
        team.setMembers(members);
        team.setMembersCount(members.size());
        return this.save(team);
    }

    private boolean isUniqueEmail(final String email) {
        return teamRepository.findTeamByEmail(email).isEmpty();
    }

    private boolean isUniqueTeamName(final String name) {
        return teamRepository.findTeamByName(name).isEmpty();
    }

    private Set<String> parseMembers(final String members) {
        return Arrays.stream(members.split(","))
                .collect(Collectors.toSet());
    }
}
