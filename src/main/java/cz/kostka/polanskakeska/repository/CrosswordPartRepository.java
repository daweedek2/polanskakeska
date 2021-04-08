package cz.kostka.polanskakeska.repository;

import cz.kostka.polanskakeska.entity.CrosswordPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CrosswordPartRepository extends JpaRepository<CrosswordPart, Long> {
    CrosswordPart getPartById(final Long cacheNumber);
    List<CrosswordPart> findByIdIn(final Collection<Long> ids);
}
