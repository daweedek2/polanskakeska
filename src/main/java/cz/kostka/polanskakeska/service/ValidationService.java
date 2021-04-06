package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.dto.ResultDTO;
import cz.kostka.polanskakeska.dto.ValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final TeamService teamService;
    private final CacheService cacheService;

    @Autowired
    public ValidationService(final TeamService teamService, final CacheService cacheService) {
        this.teamService = teamService;
        this.cacheService = cacheService;
    }

    public ResultDTO verify(final ValidationDTO validationDTO) {
        final var team = teamService.getTeamByName(validationDTO.getTeamName());
        if (team.isEmpty()) {
            return new ResultDTO(false, "Zadaný tým neexistuje. Kontaktujte pořadatele.", "");
        }

        final var cache = cacheService.getCacheByNumber(validationDTO.getCacheNumber());
        if (cache.isEmpty()) {
            return new ResultDTO(false, "Zadané číslo kešky neexistuje. Kontaktujte pořadatele.", "");
        }


        final var answer = validationDTO.getAnswer();
        if (!cache.get().getPasswords().contains(answer)) {
            return new ResultDTO(false, "Zadané řešení není správné. Zkuste to znovu :)", "");
        } else {
            final var successfulTeam = team.get();
            successfulTeam.getSolvedCaches().add(cache.get());
            teamService.save(successfulTeam);

            return new ResultDTO(true, "Správně!!!", cache.get().getCode());
        }
    }
}
