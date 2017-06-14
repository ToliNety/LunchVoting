DELETE FROM menus;
DELETE FROM votes;
DELETE FROM lunch_menus;
DELETE FROM restaurants;
DELETE FROM dishes;
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

-- menus
INSERT INTO dishes (dish_name, dish_price) VALUES
  ('Dish1R1', 100),
  ('Dish2R1', 200),
  ('Dish1R2', 150),
  ('Dish2R2', 10);

-- lunch_menus
INSERT INTO lunch_menus (restaurant_id, registered) VALUES
  (100002, '2017-06-13'),
  (100003, '2017-06-13'),
  (100002, '2017-06-12');

-- menus
INSERT INTO menus (lunch_id, dish_id) VALUES
  (100008, 100004),
  (100008, 100005),
  (100009, 100006),
  (100009, 100007),
  (100010, 100005),
  (100010, 100006);

-- votes
INSERT INTO votes (restaurant_id, user_id) VALUES
  (100002, 100000),
  (100002, 100001);

INSERT INTO votes (restaurant_id, user_id, registered) VALUES
  (100003, 100000, '2017-06-13'),
  (100003, 100001, '2017-06-13');