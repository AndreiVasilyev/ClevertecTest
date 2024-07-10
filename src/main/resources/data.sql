create database "check"
    with owner postgres;

create sequence public.disc_id_seq;

alter sequence public.disc_id_seq owner to postgres;

create table public.product
(
    id                bigserial,
    description       varchar(50),
    price             numeric(10, 2),
    quantity_in_stock integer,
    wholesale_product boolean
);

alter table public.product
    owner to postgres;

create table public.discount_card
(
    id     bigint default nextval('disc_id_seq'::regclass) not null,
    number integer,
    amount smallint
);

alter table public.discount_card
    owner to postgres;

alter sequence public.disc_id_seq owned by public.discount_card.id;

