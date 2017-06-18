DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS lunch_menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE restaurants
(
  id   INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('global_seq'),
  name VARCHAR             NOT NULL
);
CREATE UNIQUE INDEX restaurant_name_idx
  ON restaurants (name);

CREATE TABLE dishes
(
  id            INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('global_seq'),
  dish_name     VARCHAR             NOT NULL,
  dish_price    INTEGER             NOT NULL,
  restaurant_id INTEGER             NOT NULL,
  deleted       BOOL                         DEFAULT FALSE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE lunch_menus
(
  id            INTEGER PRIMARY KEY NOT NULL  DEFAULT nextval('global_seq'),
  restaurant_id INTEGER             NOT NULL,
  registered    DATE                NOT NULL  DEFAULT current_date,

  CONSTRAINT lunch_restaurant_date_idx UNIQUE (restaurant_id, registered),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menus
(
  lunch_id INTEGER NOT NULL,
  dish_id  INTEGER NOT NULL,

  CONSTRAINT lunch_dish_idx UNIQUE (lunch_id, dish_id),
  FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE,
  FOREIGN KEY (lunch_id) REFERENCES lunch_menus (id) ON DELETE CASCADE
);

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY    DEFAULT nextval('global_seq'),
  name       VARCHAR   NOT NULL,
  email      VARCHAR   NOT NULL,
  password   VARCHAR   NOT NULL,
  registered TIMESTAMP NOT NULL     DEFAULT now(),
  enabled    BOOL                   DEFAULT TRUE
);

CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id         INTEGER PRIMARY KEY  DEFAULT nextval('global_seq'),
  lunch_id   INTEGER NOT NULL,
  user_id    INTEGER NOT NULL,
  registered DATE    NOT NULL     DEFAULT current_date,
  CONSTRAINT votes_idx UNIQUE (user_id, registered),
  FOREIGN KEY (lunch_id) REFERENCES lunch_menus (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);