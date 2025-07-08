package com.example.demo.controllers;

import com.example.demo.dtos.SongDTO;
import com.example.demo.models.Song;
import com.example.demo.services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/data/songs")
public class SongsController {

    private SongService songService;

    public SongsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{songId}")
    public SongDTO getSongById(@PathVariable("songId") int id) {
        SongDTO result = songService.getSongById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song with id " + id + " not found");
        }

        return result;
    }

    @GetMapping("/sorted")
    public Collection<SongDTO> getSortedSongs(
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        return songService.getAllSongsSorted(sort);
    }

    @GetMapping
    public Collection<SongDTO> getSongs() {
        return songService.getSongs();
    }

    @PostMapping
    public SongDTO addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @PutMapping("/{songId}")
    public SongDTO updateSong(@RequestBody Song newSong, @PathVariable("songId") Integer songId) {
        return songService.updateSong(newSong, songId);
    }

    @DeleteMapping("/{songId}")
    public void deleteSongById(@PathVariable("songId") int songId) {
        songService.deleteSongById(songId);
    }
}