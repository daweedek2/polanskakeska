package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.utils.AnswerUtil;
import cz.kostka.polanskakeska.utils.CacheResultUtil;
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
            return CacheResultUtil.createFailedResult("Zadaný tým neexistuje. Kontaktujte pořadatele.");
        }

        final var cache = cacheService.getCacheByNumber(validationDTO.getCacheNumber());
        if (cache.isEmpty()) {
            return CacheResultUtil.createFailedResult("Zadané číslo kešky neexistuje. Kontaktujte pořadatele.");
        }

        final var answer = AnswerUtil.normalize(validationDTO.getAnswer());
        if (!cache.get().getPasswords().contains(answer)) {
            return CacheResultUtil.createFailedResult("Zadané řešení není správné. Zkuste to znovu :)");
        }

        teamService.addSolvedCache(team.get(), cache.get());
        return CacheResultUtil.createSuccessfulResult("Správně!!!", cache.get());
    }
}
