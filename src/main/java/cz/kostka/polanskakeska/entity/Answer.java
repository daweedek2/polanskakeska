package cz.kostka.polanskakeska.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Table
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cache cache;

    @Column
    private String text;

    @Column
    private LocalDateTime timestamp;

    public Answer(final Team team, final Cache cache, final String text) {
        this.team = team;
        this.cache = cache;
        this.text = text;
        this.timestamp = getCurrentTime();
    }

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(final Cache cache) {
        this.cache = cache;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private static LocalDateTime getCurrentTime() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()),
                TimeZone.getDefault().toZoneId());
    }
}
