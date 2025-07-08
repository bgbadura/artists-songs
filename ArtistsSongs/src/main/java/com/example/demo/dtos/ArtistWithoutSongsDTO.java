package com.example.demo.dtos;


import java.time.LocalDate;

public class ArtistWithoutSongsDTO {
    private int id;
    private String nickname;
    private LocalDate dateOfBirth;

    public ArtistWithoutSongsDTO(int id, String nickname, LocalDate dateOfBirth) {
        this.id = id;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
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
}
