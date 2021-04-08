package cz.kostka.polanskakeska.repository;

import cz.kostka.polanskakeska.entity.Crossword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrosswordRepository extends JpaRepository<Crossword, Long> {
    Crossword getCrosswordById(final Long id);
}
