package com.example.demo.services;

import com.example.demo.dtos.ArtistDTO;
import com.example.demo.models.Artist;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface ArtistService {
    ArtistDTO getArtistById(int id);

    Collection<ArtistDTO> getArtists();

    Collection<ArtistDTO> getAllArtistsSorted(Sort sort);

    ArtistDTO addArtist(Artist artist);

    ArtistDTO updateArtist(Artist artist, Integer artistId);

    void deleteArtistById(int artistId);
}
