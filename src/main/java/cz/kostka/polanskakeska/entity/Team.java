package cz.kostka.polanskakeska.entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Set<Cache> solvedCaches;

    public Team(final Long id, final String name, final Set<Cache> solvedCaches) {
        this.id = id;
        this.name = name;
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
}
