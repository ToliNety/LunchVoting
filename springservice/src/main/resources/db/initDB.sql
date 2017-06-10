DROP TABLE IF EXISTS lunch_menus;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE restaurants
(
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX restaurant_name_idx ON restaurants (name);

CREATE TABLE lunch_menus
(
    id INTEGER      PRIMARY KEY NOT NULL DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER NOT NULL,
    dish_name       VARCHAR NOT NULL,
    dish_price      INTEGER NOT NULL,

    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    email       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL,
    registered  TIMESTAMP DEFAULT now(),
    enabled     BOOL DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id     INTEGER NOT NULL,
    role        VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE votes
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER NOT NULL,
    user_id         INTEGER NOT NULL,
    registered      DATE DEFAULT current_date,
    CONSTRAINT votes_idx UNIQUE (restaurant_id, user_id, registered),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);