DROP SCHEMA IF EXISTS javajet;

CREATE SCHEMA IF NOT EXISTS `javajet`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE javajet;

DROP TABLE IF EXISTS flight_users;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS aircraft;
DROP TABLE IF EXISTS airport;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users_roles;


CREATE TABLE aircraft
(
  aircraft_id BIGINT PRIMARY KEY NOT NULL,
  model       VARCHAR(255)       NOT NULL,
  reg_number  VARCHAR(255)       NOT NULL
);
CREATE UNIQUE INDEX aircraft_reg_number_key
  ON aircraft (reg_number);
CREATE TABLE airport
(
  iata_code   VARCHAR(10) PRIMARY KEY NOT NULL,
  name_eng    VARCHAR(255),
  city_eng    VARCHAR(255),
  country_eng VARCHAR(255),
  longitude   DOUBLE PRECISION,
  latitude    DOUBLE PRECISION
);
CREATE TABLE flight
(
  flight_id          BIGINT PRIMARY KEY NOT NULL,
  departure_time     TIMESTAMP          NOT NULL,
  aircraft_id        BIGINT             NOT NULL,
  departure          VARCHAR(10)        NOT NULL,
  destination        VARCHAR(10)        NOT NULL,
  departure_timezone VARCHAR(60),
  version            INTEGER            NOT NULL,
  CONSTRAINT flight_aircraft_id_fkey FOREIGN KEY (aircraft_id) REFERENCES aircraft (aircraft_id),
  CONSTRAINT flight_from_fkey FOREIGN KEY (departure) REFERENCES airport (iata_code),
  CONSTRAINT flight_to_fkey FOREIGN KEY (destination) REFERENCES airport (iata_code)
);
CREATE TABLE position
(
  position_id BIGINT PRIMARY KEY    NOT NULL,
  title       VARCHAR(255)          NOT NULL,
  air_crew    BOOLEAN DEFAULT FALSE NOT NULL
);
CREATE UNIQUE INDEX role_title_key
  ON position (title);
CREATE TABLE role
(
  role_id BIGINT PRIMARY KEY NOT NULL,
  title   VARCHAR(64)
);
CREATE TABLE users
(
  user_id     BIGINT PRIMARY KEY NOT NULL,
  position_id BIGINT             NOT NULL,
  first_name  VARCHAR(255)       NOT NULL,
  second_name VARCHAR(255)       NOT NULL,
  username    VARCHAR(255)       NOT NULL,
  password    VARCHAR(255)       NOT NULL,
  email       VARCHAR(255)       NOT NULL,
  working     BOOLEAN            NOT NULL,
  version     INTEGER            NOT NULL,
  CONSTRAINT users_position FOREIGN KEY (position_id) REFERENCES position (position_id)
);
CREATE UNIQUE INDEX users_username_key
  ON users (username);
CREATE UNIQUE INDEX users_email_key
  ON users (email);
CREATE TABLE flight_users
(
  flight_id BIGINT NOT NULL,
  user_id   BIGINT NOT NULL,
  CONSTRAINT flight_users_pkey PRIMARY KEY (flight_id, user_id),
  CONSTRAINT flight_users_flight_id_fkey FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  CONSTRAINT flight_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE users_roles
(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES role (role_id)
);