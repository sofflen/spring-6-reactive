create table if not exists beer
(
    id               integer not null primary key auto_increment,
--     Postgres
--     id               integer not null primary key generated always as identity,
    created_at        timestamp,
    updated_at        timestamp,
    beer_name        varchar(255),
    beer_style       varchar(255),
    upc              varchar(25),
    quantity_on_hand integer,
    price            numeric(38, 2)
);