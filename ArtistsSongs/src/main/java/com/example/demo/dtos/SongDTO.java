package com.example.demo.dtos;


import java.time.LocalDate;
import java.util.Collection;

public class SongDTO {
    private int id;
    private String title;
    private LocalDate releaseDate;
    private int durationMin;
    private int durationSec;
    private Collection<ArtistWithoutSongsDTO> artists;

    public SongDTO(int id, String title, LocalDate releaseDate, int durationMin, int durationSec, Collection<ArtistWithoutSongsDTO> artists) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.durationMin = durationMin;
        this.durationSec = durationSec;
        this.artists = artists;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public Collection<ArtistWithoutSongsDTO> getArtists() {
        return artists;
    }
}
