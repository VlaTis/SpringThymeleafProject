INSERT INTO product_category(name)
VALUES ('Shafts'),
       ('Bearings'),
       ('Couplings');


INSERT INTO operation_station_status(name)
VALUES ('Busy'),
       ('Idle');

INSERT INTO operation_station(name, operation_station_status)
VALUES ('Shaft and Coupling', 2),
       ('Bearings', 2);


INSERT INTO product_status(name)
VALUES ('Available'),
       ('Not available');

INSERT INTO production_order_status(name)
VALUES ('Finished'),
       ('In Progress'),
       ('Ready for Production'),
       ('Paused');


INSERT INTO product (product_id, name, quantity_in_stock, price, description, operation_station, product_status,
                     product_category)
VALUES ('e4dbc123-a7c2-4bee-a519-e1b9ba991341', 'Shaft1', 200, 100, 'Standard copper shaft', 1, 1, 1),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991342', 'Shaft2', 50, 120, 'Standard alu shaft', 1, 1, 1),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991343', 'Shaft3', 20, 140, 'Standard alu shaft, Large', 1, 1, 1),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991344', 'Shaft4', 200, 150, 'Standard copper shaft, Large', 1, 1, 1),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991345', 'Bearing1', 150, 100, 'Standard ball bearing', 2, 1, 2),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991346', 'Bearing2', 200, 50, 'Standard ball bearing with rings', 2, 1, 2),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991347', 'Bearing3', 500, 50, 'Standard needle bearing', 2, 1, 2),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991348', 'Bearing4', 20, 70, 'Standard needle bearing with rings', 2, 1, 2),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991358', 'Bearing5', 20, 200, 'Standard ball bearing Large', 2, 2, 2),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991368', 'Coupling1', 20, 100, 'Standard coupling', 1, 1, 3),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991378', 'Coupling2', 20, 150, 'Standard coupling with bumper', 1, 1, 3),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991388', 'Coupling3', 20, 200, 'Standard coupling Large', 1, 1, 3),
       ('e4dbc123-a7c2-4bee-a519-e1b9ba991349', 'Coupling4', 16, 100, 'Standard coupling Extra Large', 1, 1, 3);


INSERT INTO production_order (name, quantity, production_order_status, product_id)
VALUES ('P-Order 1', 100, 1, 1),
       ('P-Order 2', 50, 3, 2),
       ('P-Order 3', 100, 3, 3),
       ('P-Order 4', 100, 3, 6),
       ('P-Order 5', 100, 1, 5),
       ('P-Order 6', 100, 3, 6),
       ('P-Order 7', 100, 3, 6),
       ('P-Order 8', 100, 3, 6),
       ('P-Order 9', 100, 3, 6),
       ('P-Order 10', 100, 3, 6),
       ('P-Order 11', 100, 3, 6),
       ('P-Order 12', 100, 3, 6),
       ('P-Order 13', 100, 3, 6),
       ('P-Order 14', 100, 1, 5);


INSERT INTO users(name, surname, email, password)
VALUES
    ('Admin', 'Pavardenis', 'admin@eshop.lt', '{bcrypt}$2a$10$kf8cJpZFe1z3hrI9O/Cjnuh.SKShYOMEGlYigeKxRvwqBAQ4jKJze'), -- pass is admin
    ('Operator', 'Pavardenis', 'op@eshop.lt', '{bcrypt}$2a$10$kf8cJpZFe1z3hrI9O/Cjnuh.SKShYOMEGlYigeKxRvwqBAQ4jKJze'),
    ('User', 'Pavardenis', 'user@eshop.lt', '{bcrypt}$2a$10$auHiOfM5qK7.M2ghqP5X/.U2XOa2OjADI7X/6cM9MEI3HglrZuWLW'); -- pass is user

INSERT INTO authority(name)
VALUES ('ADMIN'),
       ('OPERATOR'),
       ('USER');

INSERT INTO users_authorities(user_id, authorities_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 3);



