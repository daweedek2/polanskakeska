package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.entity.CrosswordPart;
import cz.kostka.polanskakeska.repository.CacheRepository;
import cz.kostka.polanskakeska.utils.CrosswordPartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CacheService {
    private final CacheRepository cacheRepository;
    private final CrosswordPartService crosswordPartService;

    @Autowired
    public CacheService(final CacheRepository cacheRepository,
                        final CrosswordPartService crosswordPartService) {
        this.cacheRepository = cacheRepository;
        this.crosswordPartService = crosswordPartService;
    }

    public List<Cache> getAllCaches() {
        return cacheRepository.findAll();
    }

    public List<Integer> getAllCachesNumbers() {
        return cacheRepository.findAll().stream()
                .map(Cache::getNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    public Optional<Cache> getCacheByNumber(final int number) {
        return cacheRepository.findCacheByNumber(number);
    }

    public Cache save(final Cache cache) {
        CrosswordPart part = CrosswordPartUtil.getEmptyPartForCache(cache.getNumber());
        part.setCode(cache.getCode());
        crosswordPartService.save(part);
        cache.setPart(part);
        return cacheRepository.save(cache);
    }
}
