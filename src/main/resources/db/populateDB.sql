DELETE FROM lunch_menus;
DELETE FROM votes;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM user_roles;

ALTER SEQUENCE global_seq RESTART WITH 100000;

-- users
INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'user'),
  ('Admin', 'admin@gmail.com', 'admin');

-- user_roles
INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

-- restaurants
INSERT INTO restaurants (name) VALUES
  ('Restaurant1'),
  ('Restaurant2');

-- lunch_menus
INSERT INTO lunch_menus (restaurant_id, dish_name, dish_price) VALUES
  (100002, 'Dish1R1', 100),
  (100002, 'Dish2R1', 200),
  (100003, 'Dish1R2', 150),
  (100003, 'Dish2R2', 10);

-- votes
INSERT INTO votes (restaurant_id, user_id, registered) VALUES
  (100002, 100000, '2017-03-05'),
  (100003, 100000, '2017-03-05'),
  (100002, 100001, '2017-03-05'),
  (100003, 100001, '2017-03-05');