package cz.kostka.polanskakeska.dto;

import cz.kostka.polanskakeska.entity.Word;

import java.util.List;

public class CrosswordDetailDTO {
    private List<Word> words;

    public CrosswordDetailDTO(final List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(final List<Word> words) {
        this.words = words;
    }
}
