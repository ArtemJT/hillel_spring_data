-- Создание схемы
CREATE SCHEMA IF NOT EXISTS my_store;

-- Создание последовательности
CREATE SEQUENCE IF NOT EXISTS my_store.my_store_id_seq;

-- Создание таблицы
CREATE TABLE IF NOT EXISTS my_store.user
(
    id integer NOT NULL DEFAULT nextval('my_store.my_store_id_seq'),
    creation_date    date,
    email            text NOT NULL,
    creation_time    timestamp WITHOUT time zone,
    status           text NOT NULL
    );

-- Создание индекса
CREATE INDEX IF NOT EXISTS idx_user_status ON my_store.user USING hash (status);
