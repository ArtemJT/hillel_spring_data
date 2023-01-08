-- Создание схемы
CREATE SCHEMA IF NOT EXISTS store_db;

-- Создание последовательностей
CREATE SEQUENCE IF NOT EXISTS store_db.product_prod_id_seq AS INTEGER;

CREATE SEQUENCE IF NOT EXISTS store_db.receipt_id_seq AS INTEGER;

-- Создание таблиц
CREATE TABLE IF NOT EXISTS store_db.receipt
(
    id integer NOT NULL DEFAULT nextval
(
    'store_db.receipt_id_seq'
),
    primary key (id),
    creation_date date,
    cost double precision
    );

CREATE TABLE IF NOT EXISTS store_db.product
(
    prod_id integer NOT NULL DEFAULT nextval
        (
            'store_db.product_prod_id_seq'
        ),
    primary key (prod_id),
    product_name text NOT NULL,
    cost double precision
);

CREATE TABLE IF NOT EXISTS store_db.receipt_products
(
    order_id int,
    products_prod_id int
);

ALTER SEQUENCE store_db.product_prod_id_seq OWNED BY store_db.product.prod_id;
ALTER SEQUENCE store_db.receipt_id_seq OWNED BY store_db.receipt.id;
