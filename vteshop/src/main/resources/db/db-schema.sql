DROP TABLE IF EXISTS operation_station;
CREATE TABLE operation_station
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    CONSTRAINT operation_station_key UNIQUE (name)
);

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    CONSTRAINT product_category_key UNIQUE (name)
);

DROP TABLE IF EXISTS product_status;
CREATE TABLE product_status
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    CONSTRAINT product_status_key UNIQUE (name)
);
DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id        UUID           NOT NULL,
    name              VARCHAR(20)    NOT NULL,
    quantity_in_stock INT            NOT NULL,
    price             DECIMAL(20, 2) NOT NULL,
    description       VARCHAR(500) DEFAULT NULL,
    operation_station BIGINT references operation_station(id),
    product_status    BIGINT references product_status(id),
    product_category    BIGINT references product_category(id)

);


DROP TABLE IF EXISTS production_order_status;
CREATE TABLE production_order_status
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    CONSTRAINT production_order_status_key UNIQUE (name)
);

DROP TABLE IF EXISTS production_order;
CREATE TABLE production_order
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    quantity   INT NOT NULL,
    production_order_status  BIGINT references production_order_status(id),
    product_id  BIGINT references product(id)


);







