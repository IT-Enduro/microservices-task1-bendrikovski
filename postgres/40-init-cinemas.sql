-- Cinemas
\connect cinema program
begin;

create TABLE if not exists cinema
(
    cinema_uid uuid default gen_random_uuid () PRIMARY KEY,
    name       VARCHAR(255),
    address    VARCHAR(255)
);

create unique index udx_cinema_uid on cinema (cinema_uid);

insert into cinema (cinema_uid, name, address)
values ('06cc4ba3-ee97-4d29-a814-c40588290d17', 'Кинотеатр Москва', 'Ереван, улица Хачатура Абовяна, 18');
COMMIT;

begin;
-- Film session
create TABLE if not exists film_sessions
(
    session_uid  uuid      default gen_random_uuid () PRIMARY KEY,
    film_uid     uuid      NOT NULL,
    total_seats  INT       NOT NULL,
    booked_seats INT       NOT NULL DEFAULT 0
        CHECK ( booked_seats < total_seats ),
    date         TIMESTAMP NOT NULL,
    cinema_uid    uuid
        CONSTRAINT fk_film_session_cinema_id REFERENCES cinema (cinema_uid)
);

create unique index udx_film_session_session_uid on film_sessions (session_uid);

insert into film_sessions (cinema_uid, film_uid, date, total_seats, booked_seats)
values ('06cc4ba3-ee97-4d29-a814-c40588290d17', '049161bb-badd-4fa8-9d90-87c9a82b0668', '2024-01-01T08:00:00', 5000, 0);

COMMIT;