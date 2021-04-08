package cz.kostka.polanskakeska.entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
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

    @OneToOne
    private CrosswordPart crosswordPart;

    public Cache(final Long id, final int number, final String code, final Set<String> passwords, final CrosswordPart crosswordPart) {
        this.id = id;
        this.number = number;
        this.code = code;
        this.passwords = passwords;
        this.crosswordPart = crosswordPart;
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

    public CrosswordPart getPart() {
        return crosswordPart;
    }

    public void setPart(final CrosswordPart crosswordPart) {
        this.crosswordPart = crosswordPart;
    }
}