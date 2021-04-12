package cz.kostka.polanskakeska.repository;

import cz.kostka.polanskakeska.entity.Answer;
import cz.kostka.polanskakeska.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByTeam(Team team);
}
