--liquibase formatted sql
--changeset Valkanouski:06-10-2024-created-author-tbl runOnChange:false

create table users
(
    id      bigint    not null
        constraint user_pkey
            primary key,
    name    varchar   not null,
    surname varchar   not null,
    email   varchar   not null
        constraint user_email_key
            unique,
    created timestamp not null,
    changed timestamp not null
);



