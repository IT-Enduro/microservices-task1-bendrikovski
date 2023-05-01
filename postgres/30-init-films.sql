-- Films

\connect films program

BEGIN;
create TABLE if not exists films
(
    film_uid uuid          default gen_random_uuid () PRIMARY KEY,
    name     VARCHAR(255)  NOT NULL,
    rating   NUMERIC(8, 2) NOT NULL DEFAULT 10
        CHECK ( rating BETWEEN 0 AND 10 ),
    director VARCHAR(255),
    producer VARCHAR(255),
    genre    VARCHAR(255)  NOT NULL
);

create unique index udx_film_uid on films (film_uid);

insert into films (film_uid, name, rating, director, producer, genre )
values (
    '049161bb-badd-4fa8-9d90-87c9a82b0668',
     'Terminator 2 Judgment day',
     8.6,
     'James Cameron',
     'James Cameron',
     'Sci-Fi');

COMMIT;