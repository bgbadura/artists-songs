package com.example.demo.services;

import com.example.demo.dtos.SongDTO;
import com.example.demo.models.Song;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface SongService {
    SongDTO getSongById(int id);

    Collection<SongDTO> getSongs();

    Collection<SongDTO> getAllSongsSorted(Sort sort);

    SongDTO addSong(Song song);

    SongDTO updateSong(Song song, Integer songId);

    void deleteSongById(int songId);
}
