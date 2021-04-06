package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CacheService {
    private final CacheRepository cacheRepository;

    @Autowired
    public CacheService(final CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    public List<Cache> getAllCaches() {
        return cacheRepository.findAll();
    }

    public Optional<Cache> getCacheByNumber(final int number) {
        return cacheRepository.findCacheByNumber(number);
    }

    public Cache save(final Cache cache) {
        return cacheRepository.save(cache);
    }
}
