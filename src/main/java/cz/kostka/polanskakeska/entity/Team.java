package cz.kostka.polanskakeska.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int membersCount;

    @Column
    private String email;

    @ElementCollection
    private Set<String> members;

    @ElementCollection
    @CollectionTable(name = "solved_cache_timestamps",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cache_number")
    @Column(name = "solved_caches_timestamps")
    private Map<String, LocalDateTime> solvedCachesTimestamps = new HashMap<>();

    @ElementCollection
    private Set<Cache> solvedCaches = new HashSet<>();

    @OneToOne
    private Crossword crossword;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Answer> answers;

    public Team(final Long id, final String name, final int membersCount, final String email,
                final Map<String, LocalDateTime> solvedCachesTimestamps,
                final Set<Cache> solvedCaches, final Crossword crosswordId,
                final Set<String> members, final List<Answer> answers) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.email = email;
        this.solvedCachesTimestamps = solvedCachesTimestamps;
        this.solvedCaches = solvedCaches;
        this.answers = answers;
        this.crossword = crossword;
        this.members = members;
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Cache> getSolvedCaches() {
        return solvedCaches;
    }

    public void setSolvedCaches(final Set<Cache> solvedCaches) {
        this.solvedCaches = solvedCaches;
    }

    public Map<String, LocalDateTime> getSolvedCachesTimestamps() {
        return solvedCachesTimestamps;
    }

    public void setSolvedCachesTimestamps(final Map<String, LocalDateTime> solvedCachesTimestamps) {
        this.solvedCachesTimestamps = solvedCachesTimestamps;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(final int membersCount) {
        this.membersCount = membersCount;
    }

    public Crossword getCrossword() {
        return crossword;
    }

    public void setCrossword(final Crossword crossword) {
        this.crossword = crossword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(final Set<String> members) {
        this.members = members;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(final List<Answer> answer) {
        this.answers = answer;
    }
}
