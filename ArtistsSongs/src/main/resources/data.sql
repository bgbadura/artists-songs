INSERT INTO Artist(nickname, date_of_birth)
VALUES ('Piotr Gryfindor', '1998-02-02');
INSERT INTO Artist(nickname, date_of_birth)
VALUES ('Harold Brian', '2001-03-03');

insert into SONG(title, release_date, duration_min, duration_sec)
values ('Master of Puppets', '2000-01-01', '3', '14');
insert into SONG(title, release_date, duration_min, duration_sec)
values ('Sweden', '1970-01-01', '10', '30');
insert into SONG(title, release_date, duration_min, duration_sec)
values ('Wendigo', '1980-01-01', '0', '30');

insert into SONG_ARTIST(song_id, author_id) values (1, 1);
insert into SONG_ARTIST(song_id, author_id) values (2, 1);
insert into SONG_ARTIST(song_id, author_id) values (3, 2);
insert into SONG_ARTIST(song_id, author_id) values (2, 2);