package cz.kostka.polanskakeska.repository;

import cz.kostka.polanskakeska.entity.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CacheRepository extends JpaRepository<Cache, Long> {
    Optional<Cache> findCacheByNumber(int number);
}
