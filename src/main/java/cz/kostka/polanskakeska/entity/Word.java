package cz.kostka.polanskakeska.entity;

import java.util.List;

public class Word {
    List<CrosswordPart> parts;

    public Word(final List<CrosswordPart> parts) {
        this.parts = parts;
    }

    public Word() {
    }

    public List<CrosswordPart> getParts() {
        return parts;
    }

    public void setParts(final List<CrosswordPart> parts) {
        this.parts = parts;
    }
}
