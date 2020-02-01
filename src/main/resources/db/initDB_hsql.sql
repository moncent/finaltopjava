DROP TABLE user_vote IF EXISTS;
DROP TABLE menu_item IF EXISTS;
DROP TABLE lunch_menu IF EXISTS;
DROP TABLE lunch_meals IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id              INTEGER                     GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name            VARCHAR(255)                NOT NULL,
    email           VARCHAR(255)                NOT NULL,
    password        VARCHAR(255)                NOT NULL,
    enabled         BOOLEAN DEFAULT TRUE        NOT NULL,
    registered      TIMESTAMP DEFAULT now()     NOT NULL
);
CREATE UNIQUE INDEX users_unique_idx on users(email);

CREATE TABLE user_roles
(
    role        VARCHAR(255),
    user_id     INTEGER         NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id        INTEGER           GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name      VARCHAR(255)      NOT NULL,
    country   VARCHAR(255)      NOT NULL,
    zip       INTEGER           NOT NULL,
    city      VARCHAR(255)      NOT NULL,
    address   VARCHAR(255)      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_idx on restaurants(name);


CREATE TABLE lunch_meals
(
    id              INTEGER         GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    price           INTEGER         NOT NULL,
    restaurant_id   INTEGER         NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE lunch_menu
(
    id              INTEGER                 GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    lunch_date      DATE                    NOT NULL,
    vote_count      INTEGER DEFAULT 0       NOT NULL,
    restaurant_id   INTEGER                 NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE menu_item
(
    meals_id        INTEGER                 NOT NULL,
    lunch_menu_id   INTEGER                 NOT NULL,

    FOREIGN KEY (meals_id) REFERENCES lunch_meals(id) ON DELETE CASCADE,
    FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu(id) ON DELETE CASCADE
);

CREATE TABLE user_vote
(
      user_id         INTEGER     NOT NULL,
      lunch_menu_id   INTEGER     DEFAULT NULL,
      FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
      FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu(id) ON DELETE SET NULL
);