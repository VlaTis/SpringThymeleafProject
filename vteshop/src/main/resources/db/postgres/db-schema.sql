DROP TABLE IF EXISTS operation_station_status;
CREATE TABLE operation_station_status
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT operation_station_status_key UNIQUE (name)
);


DROP TABLE IF EXISTS operation_station;
CREATE TABLE operation_station
(
    id                       BIGSERIAL PRIMARY KEY,
    name                     VARCHAR(50) NOT NULL,
    operation_station_status BIGINT references operation_station_status (id),
    CONSTRAINT operation_station_key UNIQUE (name),
    FOREIGN KEY (operation_station_status) REFERENCES operation_station_status (id)
);

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT product_category_key UNIQUE (name)
);

DROP TABLE IF EXISTS product_status;
CREATE TABLE product_status
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT product_status_key UNIQUE (name)
);
DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id                BIGSERIAL PRIMARY KEY,
    product_id        UUID           NOT NULL,
    name              VARCHAR(20)    NOT NULL,
    quantity_in_stock INT            NOT NULL,
    price             DECIMAL(20, 2) NOT NULL,
    description       VARCHAR(500) DEFAULT NULL,
    operation_station BIGINT references operation_station (id),
    product_status    BIGINT references product_status (id),
    product_category  BIGINT references product_category (id),
    FOREIGN KEY (product_status) REFERENCES product_status (id),
    FOREIGN KEY (operation_station) REFERENCES operation_station (id),
    FOREIGN KEY (product_category) REFERENCES product_category (id)


);


DROP TABLE IF EXISTS production_order_status;
CREATE TABLE production_order_status
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT production_order_status_key UNIQUE (name)
);


DROP TABLE IF EXISTS production_order;
CREATE TABLE production_order
(
    id                      BIGSERIAL PRIMARY KEY,
    name                    VARCHAR(50) NOT NULL,
    quantity                INT         NOT NULL,
    production_order_status BIGINT references production_order_status (id),
    product_id              BIGINT references product (id)


);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(20)  NOT NULL,
    surname  VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT users_key UNIQUE (email)
);

DROP TABLE IF EXISTS authority;
CREATE TABLE authority
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT authority_key UNIQUE (name)
);

DROP TABLE IF EXISTS users_authorities;
CREATE TABLE users_authorities
(
    user_id        BIGINT NOT NULL,
    authorities_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (authorities_id) REFERENCES authority (id)
);

DROP TABLE IF EXISTS sales_order;
CREATE TABLE sales_order
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(150)   NOT NULL,
    total_price DECIMAL(30, 2) NOT NUll
);








