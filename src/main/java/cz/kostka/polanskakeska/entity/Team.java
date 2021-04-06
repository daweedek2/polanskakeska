package cz.kostka.polanskakeska.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
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

    @ElementCollection
    @CollectionTable(name = "solved_cache_timestamps",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cache_number")
    @Column(name = "solved_caches_timestamps")
    private Map<String, LocalDateTime> solvedCachesTimestamps = new HashMap<>();

    @ElementCollection
    private Set<Cache> solvedCaches = new HashSet<>();

    public Team(final Long id, final String name, final Map<String, LocalDateTime> solvedCachesTimestamps, final Set<Cache> solvedCaches) {
        this.id = id;
        this.name = name;
        this.solvedCachesTimestamps = solvedCachesTimestamps;
        this.solvedCaches = solvedCaches;
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
}
