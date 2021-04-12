package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.Answer;
import cz.kostka.polanskakeska.entity.Team;
import cz.kostka.polanskakeska.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(final AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer save(final Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersForTeam(final Team team) {
        return answerRepository.findAllByTeam(team);
    }
}
