DROP TABLE IF EXISTS flight_users;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS aircraft;
DROP TABLE IF EXISTS airport;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;

CREATE TABLE aircraft
(
  aircraft_id BIGINT PRIMARY KEY NOT NULL,
  model       VARCHAR(255)       NOT NULL,
  reg_number  VARCHAR(255)       NOT NULL
);
CREATE TABLE airport
(
  airport_id  BIGINT PRIMARY KEY NOT NULL,
  iata_code   VARCHAR(10)        NOT NULL,
  name_rus    VARCHAR(255),
  name_eng    VARCHAR(255),
  city_rus    VARCHAR(255),
  city_eng    VARCHAR(255),
  country_rus VARCHAR(255),
  country_eng VARCHAR(255)
);
CREATE TABLE flight
(
  flight_id      BIGINT PRIMARY KEY NOT NULL,
  departure_time TIMESTAMP          NOT NULL,
  aircraft_id    BIGINT             NOT NULL,
  "from"         BIGINT             NOT NULL,
  "to"           BIGINT             NOT NULL,
  CONSTRAINT flight_aircraft_id_fkey FOREIGN KEY (aircraft_id) REFERENCES aircraft (aircraft_id),
  CONSTRAINT flight_from_fkey FOREIGN KEY ("from") REFERENCES airport (airport_id),
  CONSTRAINT flight_to_fkey FOREIGN KEY ("to") REFERENCES airport (airport_id)
);
CREATE TABLE role
(
  role_id BIGINT PRIMARY KEY NOT NULL,
  title   VARCHAR(255)       NOT NULL
);
CREATE TABLE users
(
  user_id     BIGINT PRIMARY KEY NOT NULL,
  role_id     BIGINT             NOT NULL,
  first_name  VARCHAR(255)       NOT NULL,
  second_name VARCHAR(255)       NOT NULL,
  username    VARCHAR(255)       NOT NULL,
  password    VARCHAR(255)       NOT NULL,
  email       VARCHAR(255)       NOT NULL,
  CONSTRAINT users_role FOREIGN KEY (role_id) REFERENCES role (role_id)
);
CREATE TABLE flight_users
(
  id        BIGINT PRIMARY KEY    NOT NULL,
  flight_id BIGINT                NOT NULL,
  user_id   BIGINT                NOT NULL,
  confirmed BOOLEAN DEFAULT FALSE NOT NULL,
  CONSTRAINT flight_users_flight_id_fkey FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  CONSTRAINT flight_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (user_id)
);