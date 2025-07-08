package com.example.demo.dtos;


import java.time.LocalDate;
import java.util.Collection;

public class ArtistDTO {
    private int id;
    private String nickname;
    private LocalDate dateOfBirth;
    private Collection<SongWithoutArtistsDTO> songs;

    public ArtistDTO(int id, String nickname, LocalDate dateOfBirth, Collection<SongWithoutArtistsDTO> songs) {
        this.id = id;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Collection<SongWithoutArtistsDTO> getSongs() {
        return songs;
    }
}
