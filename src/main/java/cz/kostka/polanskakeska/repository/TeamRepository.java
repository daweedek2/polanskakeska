package cz.kostka.polanskakeska.repository;

import cz.kostka.polanskakeska.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamByName(String teamName);
    Optional<Team> findTeamByEmail(String email);
}
