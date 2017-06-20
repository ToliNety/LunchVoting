DELETE FROM menus;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM lunch_menus;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM user_roles;

ALTER SEQUENCE global_seq RESTART WITH 100000;

-- users
INSERT INTO users (email, password) VALUES
  ('admin@gmail.com', 'admin'),
  ('user@yandex.ru', 'user');

-- user_roles
INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001);

-- restaurants
INSERT INTO restaurants (name) VALUES
  ('Restaurant1'),
  ('Restaurant2');

-- menus
INSERT INTO dishes (dish_name, dish_price, restaurant_id) VALUES
  ('Dish1R1', 100, 100002),
  ('Dish2R1', 200, 100002),
  ('Dish1R2', 150, 100003),
  ('Dish2R2', 10, 100003);

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
  (100010, 100004),
  (100010, 100005);

-- votes
INSERT INTO votes (lunch_id, user_id) VALUES
  (100010, 100000),
  (100010, 100001);

INSERT INTO votes (lunch_id, user_id, registered) VALUES
  (100008, 100000, '2017-06-13'),
  (100009, 100001, '2017-06-13');