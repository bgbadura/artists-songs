package com.example.demo.dtos;


import java.time.LocalDate;

public class SongWithoutArtistsDTO {
    private int id;
    private String title;
    private LocalDate releaseDate;
    private int durationMin;
    private int durationSec;

    public SongWithoutArtistsDTO(int id, String title, LocalDate releaseDate, int durationMin, int durationSec) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.durationMin = durationMin;
        this.durationSec = durationSec;
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
}
