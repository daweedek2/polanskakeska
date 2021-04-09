package cz.kostka.polanskakeska.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
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

    @Column
    private Long crosswordId;

    public Team(final Long id, final String name, final int membersCount, final String email,
                final Map<String, LocalDateTime> solvedCachesTimestamps,
                final Set<Cache> solvedCaches, final Long crosswordId,
                final Set<String> members) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.email = email;
        this.solvedCachesTimestamps = solvedCachesTimestamps;
        this.solvedCaches = solvedCaches;
        this.crosswordId = crosswordId;
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

    public Long getCrosswordId() {
        return crosswordId;
    }

    public void setCrosswordId(final Long crosswordId) {
        this.crosswordId = crosswordId;
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
}
