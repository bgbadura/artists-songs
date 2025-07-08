package com.example.demo.controllers;

import com.example.demo.dtos.ArtistDTO;
import com.example.demo.dtos.SongDTO;
import com.example.demo.models.Artist;
import com.example.demo.services.ArtistService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/data/artists")
public class ArtistsController {

    private ArtistService artistService;

    public ArtistsController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{artistId}")
    public ArtistDTO getArtistById(@PathVariable("artistId") int id) {
        ArtistDTO result = artistService.getArtistById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist with id " + id + " not found");
        }

        return result;
    }

    @GetMapping
    public Collection<ArtistDTO> getArtists() {
        return artistService.getArtists();
    }

    @GetMapping("/sorted")
    public Collection<ArtistDTO> getSortedArtists(
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        return artistService.getAllArtistsSorted(sort);
    }

    @PostMapping
    public ArtistDTO addArtist(@RequestBody Artist artist) {
        if (artist.getDateOfBirth().isAfter(LocalDate.now()))
            return null;
        return artistService.addArtist(artist);
    }

    @PutMapping("/{artistId}")
    public ArtistDTO updateArtist(@RequestBody Artist artist, @PathVariable("artistId") Integer artistId) {
        if (artist.getDateOfBirth().isAfter(LocalDate.now()))
            return null;
        return artistService.updateArtist(artist, artistId);
    }

    @DeleteMapping("/{artistId}")
    public void deleteArtistById(@PathVariable("artistId") int artistId) {
        artistService.deleteArtistById(artistId);
    }
}