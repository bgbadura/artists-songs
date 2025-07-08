package com.example.demo.services;

import com.example.demo.dtos.ArtistWithoutSongsDTO;
import com.example.demo.dtos.SongDTO;
import com.example.demo.models.Song;
import com.example.demo.repositories.SongRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public SongDTO getSongById(int id) {
        Optional<Song> songById = songRepository.findById(id);

        if (songById.isEmpty()) return null;

        Song resultSong = songById.get();
        List<ArtistWithoutSongsDTO> artists = getSongArtistsByStream(resultSong);
        return new SongDTO(
                resultSong.getId(),
                resultSong.getTitle(),
                resultSong.getReleaseDate(),
                resultSong.getDurationMin(),
                resultSong.getDurationSec(),
                artists
        );
    };

    @Override
    public Collection<SongDTO> getSongs() {
        List<Song> songs = songRepository.findAll();

        return songs.stream()
                .map(song -> {
                    List<ArtistWithoutSongsDTO> artists = getSongArtistsByStream(song);

                    return new SongDTO(
                            song.getId(),
                            song.getTitle(),
                            song.getReleaseDate(),
                            song.getDurationMin(),
                            song.getDurationSec(),
                            artists
                    );
                }).toList();
    }

    @Override
    public Collection<SongDTO> getAllSongsSorted(Sort sort) {
        List<Song> songs = songRepository.findAll(sort);

        return songs.stream()
                .map(song -> {
                    List<ArtistWithoutSongsDTO> artists = getSongArtistsByStream(song);

                    return new SongDTO(
                            song.getId(),
                            song.getTitle(),
                            song.getReleaseDate(),
                            song.getDurationMin(),
                            song.getDurationSec(),
                            artists
                    );
                }).toList();
    }


    @Override
    public SongDTO addSong(Song song) {
        Song addedSong =  songRepository.save(song);

        List<ArtistWithoutSongsDTO> artists = getSongArtistsByStream(addedSong);

        return new SongDTO(
                addedSong.getId(),
                addedSong.getTitle(),
                addedSong.getReleaseDate(),
                addedSong.getDurationMin(),
                addedSong.getDurationSec(),
                artists
        );
    }

    @Override
    public SongDTO updateSong(Song newSong, Integer songId) {
        if (songRepository.findById(songId).isEmpty())
            return null;

        Song existingSong = songRepository.findById(songId).get();

        // Zmienia wartości pól na nowe, jeśli nowe są niepuste (sprawdzenie w setterach)
        // Tytuł
        existingSong.setTitle(newSong.getTitle());

        // Data wydania
        existingSong.setReleaseDate(newSong.getReleaseDate());

        // Czas trwania [min]
        existingSong.setDurationMin(newSong.getDurationMin());

        // Czas trwania [sec]
        existingSong.setDurationSec(newSong.getDurationSec());

        Song updatedSong =  songRepository.save(existingSong);
        List<ArtistWithoutSongsDTO> artists = getSongArtistsByStream(updatedSong);

        return new SongDTO(
                updatedSong.getId(),
                updatedSong.getTitle(),
                updatedSong.getReleaseDate(),
                updatedSong.getDurationMin(),
                updatedSong.getDurationSec(),
                artists
        );
    }


    @Override
    public void deleteSongById(int songId) {
        songRepository.deleteById(songId);
    }

    public List<ArtistWithoutSongsDTO> getSongArtistsByStream(Song song) {
        return song.getArtists().stream()
                .map(artist -> new ArtistWithoutSongsDTO(
                                artist.getId(),
                                artist.getNickname(),
                                artist.getDateOfBirth()
                        )
                ).toList();
    }
}
