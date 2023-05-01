-- Tickets
\connect tickets program
begin;
create TABLE if not exists tickets
(
    ticket_uid  uuid        default gen_random_uuid () PRIMARY KEY,
    film_uid    uuid        NOT NULL,
    session_uid uuid        NOT NULL,
    seat_row    INT,
    seat        INT,
    user_name   VARCHAR(80) NOT NULL,
    date        TIMESTAMP   NOT NULL,
    status      VARCHAR(20) NOT NULL
        CHECK ( status IN ('BOOKED', 'CANCELED') )
);

create unique index udx_tickets_ticket_uid on tickets (ticket_uid);

COMMIT;

