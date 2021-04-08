package cz.kostka.polanskakeska.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CrosswordPart {
    @Id
    @GeneratedValue
    private Long id;
    private int cacheNumber;
    private int word;
    private int position;
    private int letterCount;
    private String code;

    public CrosswordPart(final int cacheNumber, final int word, final int position, final int letterCount, final String code) {
        this.cacheNumber = cacheNumber;
        this.word = word;
        this.position = position;
        this.letterCount = letterCount;
        this.code = code;
    }

    public CrosswordPart() {
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(final int letterCount) {
        this.letterCount = letterCount;
    }

    public int getCacheNumber() {
        return cacheNumber;
    }

    public void setCacheNumber(final int cacheNumber) {
        this.cacheNumber = cacheNumber;
    }

    public int getWord() {
        return word;
    }

    public void setWord(final int word) {
        this.word = word;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String letters) {
        this.code = letters;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
