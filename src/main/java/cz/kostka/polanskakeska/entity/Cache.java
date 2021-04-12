package cz.kostka.polanskakeska.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cache")
public class Cache {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int number;

    @Column
    private String code;

    @ElementCollection
    private Set<String> passwords = new HashSet<>();

    @OneToMany
    private Set<CrosswordPart> crosswordParts;

    @OneToMany(
            mappedBy = "cache",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Answer> answers = new ArrayList<>();

    public Cache(final Long id, final int number, final String code, final Set<String> passwords, final Set<CrosswordPart> crosswordParts, final List<Answer> answers) {
        this.id = id;
        this.number = number;
        this.code = code;
        this.passwords = passwords;
        this.crosswordParts = crosswordParts;
        this.answers = answers;
    }

    public Cache() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Set<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(final Set<String> passwords) {
        this.passwords = passwords;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<CrosswordPart> getCrosswordParts() {
        return crosswordParts;
    }

    public void setCrosswordParts(final Set<CrosswordPart> crosswordParts) {
        this.crosswordParts = crosswordParts;
    }

    public List<Answer> getAnswer() {
        return answers;
    }

    public void setAnswer(final List<Answer> answers) {
        this.answers = answers;
    }
}