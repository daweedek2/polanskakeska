package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.dto.CrosswordCheckDTO;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.utils.AnswerUtil;
import cz.kostka.polanskakeska.utils.ResultUtil;
import cz.kostka.polanskakeska.dto.ResultDTO;
import cz.kostka.polanskakeska.dto.CacheValidationDTO;
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

    public Team validateCrosswordRequest(final CrosswordCheckDTO dto) {
        final var team = teamService.getTeamByName(dto.getTeamName());
        if (team.isEmpty()) {
            return null;
        }

        return team.get().getMembersCount() == dto.getMemberCount() ? team.get() : null;
    }

    public ResultDTO verify(final CacheValidationDTO cacheValidationDTO) {
        final var team = teamService.getTeamByName(cacheValidationDTO.getTeamName());
        if (team.isEmpty()) {
            return ResultUtil.createFailedResult("Zadaný tým neexistuje. Kontaktujte pořadatele.");
        }

        final var cache = cacheService.getCacheByNumber(cacheValidationDTO.getCacheNumber());
        if (cache.isEmpty()) {
            return ResultUtil.createFailedResult("Zadané číslo kešky neexistuje. Kontaktujte pořadatele.");
        }

        final var answer = AnswerUtil.normalize(cacheValidationDTO.getAnswer());
        if (!cache.get().getPasswords().contains(answer)) {
            return ResultUtil.createFailedResult("Zadané řešení není správné. Zkuste to znovu :)");
        }

        teamService.addSolvedCache(team.get(), cache.get());
        return ResultUtil.createSuccessfulResult("Správně!!!", cache.get());
    }
}
