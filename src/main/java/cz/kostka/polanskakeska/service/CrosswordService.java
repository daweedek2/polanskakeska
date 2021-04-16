package cz.kostka.polanskakeska.service;

import cz.kostka.polanskakeska.dto.CrosswordDetailDTO;
import cz.kostka.polanskakeska.entity.Crossword;
import cz.kostka.polanskakeska.entity.CrosswordPart;
import cz.kostka.polanskakeska.entity.Word;
import cz.kostka.polanskakeska.repository.CrosswordRepository;
import cz.kostka.polanskakeska.utils.CrosswordPartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrosswordService {
    private static final int CROSSWORD_LENGTH = 8;
    private static final int FIRST_CACHE = 1;
    private static final int LAST_CACHE = 20;

    private CrosswordRepository crosswordRepository;
    private CrosswordPartService crosswordPartService;

    @Autowired
    public CrosswordService(final CrosswordRepository crosswordRepository,
                            final CrosswordPartService crosswordPartService) {
        this.crosswordRepository = crosswordRepository;
        this.crosswordPartService = crosswordPartService;
    }

    public CrosswordDetailDTO buildDetail(final Crossword crossword) {
        final int wordCount = crossword.getWordCount();

        final List<CrosswordPart> crosswordParts = crosswordPartService.getAllByIds(crossword.getPartMap().values());

        List<Word> words = new ArrayList<>(wordCount);

        for (int i = 0; i < wordCount; i++) {
            final int wordIndex = i + 1;
            List<CrosswordPart> singleWordParts = crosswordParts.stream()
                    .filter(part -> part.getWord() == wordIndex)
                    .sorted(Comparator.comparingInt(CrosswordPart::getPosition))
                    .collect(Collectors.toList());

            Word word = new Word();
            word.setParts(singleWordParts);
            words.add(word);
        }

        return new CrosswordDetailDTO(words);
    }

    public Crossword getById(final Long id) {
        return crosswordRepository.getCrosswordById(id);
    }

    public Crossword save(final Crossword crossword) {
        return crosswordRepository.save(crossword);
    }

    public Crossword createEmptyCrossword() {
        Crossword crossword = new Crossword();



        Map<Integer, Long> mapWithParts = new HashMap<>();

        for (int i = FIRST_CACHE; i < LAST_CACHE + 1; i++) {
            final int cacheNumber = i;
            Optional<CrosswordPart> optionalPart = crosswordPartService.getPartsOfCache(i).stream()
                    .filter(crosswordPart -> crosswordPart.getCode().contains("_"))
                    .filter(crosswordPart -> crosswordPart.getCacheNumber() == cacheNumber)
                    .findFirst();

            if (optionalPart.isPresent()) {
                mapWithParts.putIfAbsent(i, optionalPart.get().getId());
            }
        }

        crossword.setWordCount(CROSSWORD_LENGTH);
        crossword.setPartMap(mapWithParts);

        return this.save(crossword);
    }
}
