package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.entity.CrosswordPart;
import cz.kostka.polanskakeska.repository.CrosswordPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CrosswordPartService {
    private final CrosswordPartRepository crosswordPartRepository;

    @Autowired
    public CrosswordPartService(final CrosswordPartRepository crosswordPartRepository) {
        this.crosswordPartRepository = crosswordPartRepository;
    }

    public CrosswordPart getPartById(final Long id) {
        return crosswordPartRepository.getPartById(id);
    }

    public List<CrosswordPart> getAllByIds(final Collection<Long> ids) {
        return crosswordPartRepository.findByIdIn(ids);
    }

    public CrosswordPart save(final CrosswordPart crosswordPart) {
        return crosswordPartRepository.save(crosswordPart);
    }
}
