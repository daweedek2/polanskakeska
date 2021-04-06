package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cache")
public class CacheController {
    private final CacheService cacheService;


    @Autowired
    public CacheController(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/getAll")
    public List<Cache> getAll() {
        return cacheService.getAllCaches();
    }

    @PostMapping("/save")
    public Cache save(final @RequestBody Cache cache) {
        return cacheService.save(cache);
    }
}
