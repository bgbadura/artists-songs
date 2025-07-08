DROP TABLE IF EXISTS SONG_ARTIST;
DROP TABLE IF EXISTS SONG;
DROP TABLE IF EXISTS Artist;

CREATE TABLE Artist (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nickname VARCHAR(128),
                        date_of_birth DATE
);
CREATE TABLE SONG (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(256),
                      release_date DATE,
                      duration_min INT,
                      duration_sec INT
);
CREATE TABLE SONG_ARTIST (
                             song_id INT,-- NOT NULL,
                             author_id INT-- NOT NULL,
);

ALTER TABLE SONG_ARTIST
    ADD CONSTRAINT fk_song
    FOREIGN KEY (song_id) REFERENCES SONG(id)
    ON DELETE SET NULL;

ALTER TABLE SONG_ARTIST
    ADD CONSTRAINT fk_artist
    FOREIGN KEY (author_id) REFERENCES Artist(id)
    ON DELETE SET NULL;

