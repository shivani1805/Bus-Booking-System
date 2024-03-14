DROP DATABASE IF EXISTS bus_ticket_booking ;
CREATE DATABASE bus_ticket_booking;
USE bus_ticket_booking;

CREATE TABLE city
(
city_id INT PRIMARY KEY auto_increment,
city_name VARCHAR(64) UNIQUE,
state VARCHAR(64)
);

CREATE TABLE driver
(
driver_id INT PRIMARY KEY auto_increment,
first_name VARCHAR(64),
last_name VARCHAR(64),
phone_no BIGINT,
license_no VARCHAR(10) NOT NULL
);

CREATE TABLE bus_type
(
bus_id INT PRIMARY KEY auto_increment,
type enum ("sleeper", "seater","Double Decker","Shuttle"),
capacity INT,
model VARCHAR(64),
color VARCHAR(64),
license_plate VARCHAR(64) NOT NULL
);

CREATE TABLE bus_route
(
route_id INT PRIMARY KEY auto_increment,
total_fare INT,
duration INT,
start_date_time DATETIME,
end_date_time DATETIME,
origin_city_name VARCHAR(64),
destination_city_name VARCHAR(64),
driver_id INT,
bus_id INT,
FOREIGN KEY (origin_city_name) REFERENCES city(city_name) 
ON UPDATE RESTRICT ON DELETE RESTRICT,
FOREIGN KEY (destination_city_name) REFERENCES city(city_name) 
ON UPDATE RESTRICT ON DELETE RESTRICT,
FOREIGN KEY (driver_id) REFERENCES driver(driver_id) 
ON UPDATE RESTRICT ON DELETE RESTRICT,
FOREIGN KEY (bus_id) REFERENCES bus_type(bus_id) 
ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE user
(
username VARCHAR(64) PRIMARY KEY,
user_role VARCHAR(64)
);

CREATE TABLE admin
(
first_name VARCHAR(64),
last_name VARCHAR(64),
email VARCHAR(64),
phone_no BIGINT,
username VARCHAR(64),
FOREIGN KEY (username) REFERENCES user(username) 
ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE customer
(
first_name VARCHAR(64),
last_name VARCHAR(64),
email VARCHAR(64),
phone_no BIGINT,
username VARCHAR(64),
FOREIGN KEY (username) REFERENCES user(username) 
ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE bus_journey_booking
(
booking_id INT PRIMARY KEY auto_increment,
payment_method enum ("Credit Card", "Debit Card", "Online Wallet"),
amount_paid INT,
seats_booked INT,
route_id INT,
username VARCHAR(64),
FOREIGN KEY (route_id) REFERENCES bus_route(route_id) 
ON UPDATE RESTRICT ON DELETE RESTRICT,
FOREIGN KEY (username) REFERENCES customer(username) 
ON UPDATE RESTRICT ON DELETE RESTRICT
);

