package com.example.demo.services;

import com.example.demo.dtos.ArtistDTO;
import com.example.demo.dtos.ArtistWithoutSongsDTO;
import com.example.demo.dtos.SongDTO;
import com.example.demo.dtos.SongWithoutArtistsDTO;
import com.example.demo.models.Artist;
import com.example.demo.models.Song;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.SongRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final SongRepository songRepository;
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public ArtistDTO getArtistById(int id) {
        Optional<Artist> artistById = artistRepository.findById(id);

        if (artistById.isEmpty()) return null;

        Artist resultArtist = artistById.get();
        List<SongWithoutArtistsDTO> songs = getArtistSongsByStream(resultArtist);
        return new ArtistDTO(
                resultArtist.getId(),
                resultArtist.getNickname(),
                resultArtist.getDateOfBirth(),
                songs
        );
    }

    @Override
    public Collection<ArtistDTO> getArtists() {
        List<Artist> artists = artistRepository.findAll();

        return artists.stream()
                .map(artist -> {
                    List<SongWithoutArtistsDTO> songs = getArtistSongsByStream(artist);

                    return new ArtistDTO(
                        artist.getId(),
                        artist.getNickname(),
                        artist.getDateOfBirth(),
                        songs
                    );
                }).toList();
    }

    @Override
    public Collection<ArtistDTO> getAllArtistsSorted(Sort sort) {
        List<Artist> artists = artistRepository.findAll(sort);

        return artists.stream()
                .map(artist -> {
                    List<SongWithoutArtistsDTO> songs = getArtistSongsByStream(artist);

                    return new ArtistDTO(
                            artist.getId(),
                            artist.getNickname(),
                            artist.getDateOfBirth(),
                            songs
                    );
                }).toList();
    }

    @Override
    public ArtistDTO addArtist(Artist artist) {
        Collection<Song> incomingSongs = artist.getSongs();
        Collection<Song> managedSongs = new ArrayList<>();

        for (Song s : incomingSongs) {
            songRepository.findById(s.getId()).ifPresent(song -> {
                song.getArtists().add(artist);
                managedSongs.add(song);
            });
        }

        artist.setSongs(managedSongs);


        Artist addedArtist = artistRepository.save(artist);

        List<SongWithoutArtistsDTO> songs = getArtistSongsByStream(addedArtist);

        return new ArtistDTO(
                addedArtist.getId(),
                addedArtist.getNickname(),
                addedArtist.getDateOfBirth(),
                songs
        );
    }

    @Override
    public ArtistDTO updateArtist(Artist newArtist, Integer artistId) {
        if (artistRepository.findById(artistId).isEmpty())
            return null;
        Artist existingArtist = artistRepository.findById(artistId).get();

        // Zmienia wartości pól na nowe, jeśli nowe są niepuste (sprawdzenie w setterach)
        // Pseudonim
        existingArtist.setNickname(newArtist.getNickname());
        // Data urodzenia
        existingArtist.setDateOfBirth(newArtist.getDateOfBirth());

        Artist updatedArtist = artistRepository.save(existingArtist);
        List<SongWithoutArtistsDTO> songs = getArtistSongsByStream(updatedArtist);

        return new ArtistDTO(
                updatedArtist.getId(),
                updatedArtist.getNickname(),
                updatedArtist.getDateOfBirth(),
                songs
        );
    }

    @Override
    public void deleteArtistById(int artistId) {
        artistRepository.deleteById(artistId);
    }

    public List<SongWithoutArtistsDTO> getArtistSongsByStream(Artist artist) {
        return artist.getSongs().stream()
                .map(song -> new SongWithoutArtistsDTO(
                                song.getId(),
                                song.getTitle(),
                                song.getReleaseDate(),
                                song.getDurationMin(),
                                song.getDurationSec()
                        )
                ).toList();
    }
}
