package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Integer durationMin;

    private Integer durationSec;

    @ManyToMany
    @JoinTable(
            name = "SONG_ARTIST",
            joinColumns = @JoinColumn(name = "SONG_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private Collection<Artist> artists = new ArrayList<>();

    protected Song() {

    }

    public Song(int id, String title, LocalDate releaseDate, int durationMin, int durationSec) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.durationMin = durationMin;
        this.durationSec = durationSec;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", durationMin=" + durationMin +
                ", durationSec=" + durationSec +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public Integer getDurationSec() {
        return durationSec;
    }

    public Collection<Artist> getArtists() {
        return artists;
    }

    public void setTitle(String newTitle) {
        if (newTitle != null && !newTitle.isEmpty())
            this.title = newTitle;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate != null)
            this.releaseDate = releaseDate;
    }

    public void setDurationMin(Integer durationMin) {
        if (durationMin != null)
            this.durationMin = durationMin;
    }

    public void setDurationSec(Integer durationSec) {
        if (durationSec != null)
            this.durationSec = durationSec;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }
}
