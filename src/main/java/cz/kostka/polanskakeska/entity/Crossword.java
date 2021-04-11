package cz.kostka.polanskakeska.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class Crossword {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int wordCount;

    @OneToOne
    private Team team;

    @ElementCollection
    @CollectionTable(name = "parts_map",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    @MapKeyColumn(name = "part_id")
    @Column(name = "parts_map")
    private Map<Integer, Long> partMap = new HashMap<>();

    public Crossword(final Long id, final int wordCount, final Map<Integer, Long> partMap) {
        this.id = id;
        this.wordCount = wordCount;
        this.partMap = partMap;
    }

    public Crossword() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(final int wordCount) {
        this.wordCount = wordCount;
    }

    public Map<Integer, Long> getPartMap() {
        return partMap;
    }

    public void setPartMap(final Map<Integer, Long> partMap) {
        this.partMap = partMap;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }
}
