package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nickname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "artists")
    private Collection<Song> songs = new ArrayList<>();

    protected Artist() {

    }

    public Artist(Integer id, String nickname, LocalDate dateOfBirth, Collection<Song> songs) {
        this.id = id;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", songs=" + songs +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    public void setNickname(String nickname) {
        if (nickname != null && !nickname.isEmpty())
            this.nickname = nickname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth != null && !dateOfBirth.isAfter(LocalDate.now()))
            this.dateOfBirth = dateOfBirth;
    }

    public void setSongs(Collection<Song> songs) {
        this.songs = songs;
    }
}
