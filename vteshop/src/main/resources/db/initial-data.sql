INSERT INTO product_category(name)
VALUES ('Shafts'),
       ('Bearings'),
       ('Couplings');


INSERT INTO operation_station_status(name)
VALUES ('Busy'),
       ('Idle');

INSERT INTO operation_station(name, operation_station_status)
VALUES ('Shaft and Coupling Machining', 2),
       ('Bearings production', 2);


INSERT INTO product_status(name)
VALUES ('Available'),
       ('Not available');

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









