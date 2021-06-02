INSERT INTO denisenko.roles (id, title) VALUES (1, 'user');
INSERT INTO denisenko.roles (id, title) VALUES (2, 'admin');

INSERT INTO denisenko.users (`name`, password, birthday, gender, email, roleId) VALUES ('user', 'password', '2001-02-16', 'M', 'someMail@gmail.com', 1);
INSERT INTO denisenko.users (`name`, password, birthday, gender, email, roleId) VALUES ('user2', 'password', '2012-12-12', 'M', 'someMail2@gmail.com', 1);
INSERT INTO denisenko.users (`name`, password, birthday, gender, email, roleId) VALUES ('user3', 'password', '2012-12-12', 'M', 'someMail2@gmail.com', 1);
INSERT INTO denisenko.users (`name`, password, birthday, gender, email, roleId) VALUES ('admin', 'password', '1971-06-28', 'M', 'someMail4@gmail.com', 2);
INSERT INTO denisenko.users (`name`, password, birthday, gender, email, roleId) VALUES ('admin2', 'password', '1971-06-28', 'M', 'someMail5@gmail.com', 2);

INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('Hitchhiker`s Guide To The Galaxy', 100.25, '/resources/static/images/Hitchhiker Guide To The Galaxy.png', true, 4);
INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('The Color Of Magic', 115.25, '/resources/static/images/The Color Of Magic.png', true, 4);
INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('For Whom The Bell Tolls', 120.25, '/resources/static/images/For Whom The Bell Tolls.png', true, 4);
INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('Three Man In A Boat', 150.25, '/resources/static/images/Three Man In A Boat.png', true, 4);
INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('Three Comrades', 170.25, '/resources/static/images/Three Comrades.png', true, 4);
INSERT INTO denisenko.books (`name`, actualPrice, photoPath, visibility, quantityLeftInStore) VALUES ('The Peaceful Warrior`s Way', 100.25, '/resources/static/images/Peaceful Warrior Way.png', false, 4);

INSERT INTO denisenko.orders (userId, totalPrice, orderStat) VALUES(1, 100.25, 'done');
INSERT INTO denisenko.orders (userId, totalPrice, orderStat) VALUES(2, 115.25, 'done');
INSERT INTO denisenko.orders (userId, totalPrice, orderStat) VALUES(3, 170.25, 'open');
INSERT INTO denisenko.orders (userId, totalPrice, orderStat) VALUES(3, 100.25, 'open');

INSERT INTO denisenko.orderdetails (id, orderId, bookId, quantityInOrder, price) VALUES (1, 1, 1, 1, 100.25);
INSERT INTO denisenko.orderdetails (id, orderId, bookId, quantityInOrder, price) VALUES (2, 2, 2, 1, 115.25);
INSERT INTO denisenko.orderdetails (id, orderId, bookId, quantityInOrder, price) VALUES (3, 3, 5, 1, 170.25);
INSERT INTO denisenko.orderdetails (id, orderId, bookId, quantityInOrder, price) VALUES (4, 4, 1, 1, 100.25);
