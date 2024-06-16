-- liquibase formatted sql

-- changeset oshinkevich:1

CREATE TABLE bank_account
(
    account_number TEXT PRIMARY KEY NOT NULL,
    balance        DOUBLE PRECISION NOT NULL,
    start_balance  DOUBLE PRECISION NOT NULL
);

CREATE TABLE contact
(
    id     BIGSERIAL primary key NOT NULL,
    phones TEXT                  NOT NULL,
    email  TEXT                  NOT NULL
);

CREATE TYPE role AS ENUM ('USER');


CREATE TABLE user_data
(
    id              BIGSERIAL PRIMARY KEY NOT NULL,
    name            TEXT                  NOT NULL,
    surname         TEXT                  NOT NULL,
    login           TEXT                  NOT NULL,
    password        TEXT                  NOT NULL,
    role            role                  NOT NULL,
    initial_balance DOUBLE PRECISION,
    birth_date      TIMESTAMP
);

CREATE FUNCTION enum_role_from_str(text)
    RETURNS role
AS
'select $1::varchar::role'
    LANGUAGE SQL IMMUTABLE;

CREATE CAST (text AS role)
    WITH FUNCTION enum_role_from_str(text)
    AS IMPLICIT;