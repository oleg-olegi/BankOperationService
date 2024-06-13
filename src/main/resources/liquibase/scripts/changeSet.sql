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
    full_name       TEXT                  NOT NULL,
    login           TEXT                  NOT NULL,
    password        TEXT                  NOT NULL,
    role            role,
    initial_balance DOUBLE PRECISION,
    birth_date      TIMESTAMP
);
