-- liquibase formatted sql

-- changeset oshinkevich:1

CREATE TABLE bank_account
(
    account_number TEXT PRIMARY KEY NOT NULL,
    balance        DOUBLE PRECISION,
    start_balance  DOUBLE PRECISION
);

CREATE TABLE contact
(
    id     BIGINT primary key,
    phones TEXT,
    email  TEXT
);
CREATE TABLE user_data
(
    id              BIGINT PRIMARY KEY,
    full_name       TEXT,
    login           TEXT,
    password        TEXT,
    initial_balance DOUBLE PRECISION,
    birth_date      TIMESTAMP,
    email           TEXT,
    phone           TEXT
);
-- changeset oshinkevich:2
ALTER TABLE user_data
    ADD COLUMN role TEXT;
--changeset oshinkevich:3
ALTER TABLE bank_account
    ADD COLUMN user_data_id BIGINT;
ALTER TABLE bank_account
    ADD CONSTRAINT fk_user_data FOREIGN KEY (user_data_id) REFERENCES user_data (id);
--changeset oshinkevich:4
UPDATE bank_account ba
SET user_data_id = (
    SELECT u.id
    FROM user_data u
    WHERE u.bank_account_account_number = ba.account_number
    );