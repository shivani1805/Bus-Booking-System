USE bus_ticket_booking;

DELIMITER //
CREATE PROCEDURE create_booking(
    IN route_id INT,
    IN total_fare INT,
    IN payment_method enum ("Credit Card", "Debit Card", "Online Wallet"),
    IN seats INT,
    username VARCHAR(64)
        )
BEGIN
INSERT INTO bus_journey_booking (payment_method, amount_paid, seats_booked, route_id, username)
VALUES (payment_method, total_fare, seats, route_id, username);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE view_booking(booking_id int)
BEGIN
SELECT bjb.booking_id, br.origin_city_name, br.destination_city_name, br.start_date_time, br.end_date_time, br.duration, bjb.seats_booked, bjb.amount_paid, bjb.payment_method, bt.type, bt.capacity, bt.model, bt.color, d.first_name, d.last_name
FROM bus_journey_booking AS bjb
         INNER JOIN bus_route AS br ON bjb.route_id = br.route_id
         INNER JOIN bus_type AS bt ON br.bus_id = bt.bus_id
         INNER JOIN driver AS d ON br.driver_id = d.driver_id
WHERE bjb.booking_id=booking_id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE view_user(username varchar(64))
BEGIN
SELECT c.first_name, c.last_name, c.email, c.phone_no, c.username, u.user_role
FROM customer as c
         INNER JOIN user as u ON u.username = c.username
WHERE c.username=username;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE check_booking_exists(booking_id int)
BEGIN
SELECT *
FROM bus_journey_booking as bjb
WHERE bjb.booking_id=booking_id;
END//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE check_booking_ids(username varchar(64))
BEGIN
SELECT bjb.booking_id
FROM bus_journey_booking as bjb
WHERE bjb.username=username;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_booking(id int)
BEGIN
DELETE FROM bus_journey_booking WHERE bus_journey_booking.booking_id = id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_seats(p_bookingId INT, p_newSeatCount INT)
BEGIN
UPDATE bus_journey_booking SET seats_booked = p_newSeatCount WHERE bus_journey_booking.booking_id = p_bookingId;
SELECT 'Seat count update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_fn(username VARCHAR(64), new_fn VARCHAR(64))
BEGIN
UPDATE customer SET first_name = new_fn WHERE customer.username = username;
SELECT 'First name update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_adminfn(username VARCHAR(64), new_fn VARCHAR(64))
BEGIN
UPDATE admin SET first_name = new_fn WHERE admin.username = username;
SELECT 'First name update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_ln(username VARCHAR(64), new_ln VARCHAR(64))
BEGIN
UPDATE customer SET last_name = new_ln WHERE customer.username = username;
SELECT 'Last name update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_adminln(username VARCHAR(64), new_ln VARCHAR(64))
BEGIN
UPDATE admin SET last_name = new_ln WHERE admin.username = username;
SELECT 'Last name update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_e(username VARCHAR(64), new_email VARCHAR(64))
BEGIN
UPDATE customer SET email = new_email WHERE customer.username = username;
SELECT 'Email ID update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_admine(username VARCHAR(64), new_email VARCHAR(64))
BEGIN
UPDATE admin SET email = new_email WHERE admin.username = username;
SELECT 'Email ID update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_pn(username VARCHAR(64), new_pn bigint)
BEGIN
UPDATE customer SET phone_no = new_pn WHERE customer.username = username;
SELECT 'Phone number update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_adminpn(username VARCHAR(64), new_pn bigint)
BEGIN
UPDATE admin SET phone_no = new_pn WHERE admin.username = username;
SELECT 'Phone number update successfully' AS result;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE edit_route(booking_Id INT, route_id_new INT, new_fare INT)
BEGIN
UPDATE bus_journey_booking SET route_id = route_id_new WHERE bus_journey_booking.booking_id = booking_Id;
UPDATE bus_journey_booking SET amount_paid = new_fare WHERE bus_journey_booking.booking_id = booking_Id;
SELECT 'Route updated successfully' AS result;
END //

DELIMITER ;


    DELIMITER //
CREATE PROCEDURE insert_driver(
    IN firstName varchar(64),
    IN lastName varchar(64),
    IN phone BIGINT,
    IN license varchar(10)
)
BEGIN
INSERT INTO driver (first_name, last_name, phone_no, license_no)
VALUES (firstName, lastName, phone, license);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE insert_city(
    IN cityName varchar(64),
    IN stateName varchar(64)
)
BEGIN
INSERT INTO city (city_name, state)
VALUES (cityName, stateName);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE insert_bus(
    IN bus_type enum("sleeper","seater","Double Decker","Shuttle"),
    IN bus_cap int,
    IN bus_model varchar(64),
    IN bus_color varchar(64),
    IN bus_plate varchar(64)
        )
BEGIN
INSERT INTO bus_type (type, capacity,model,color,license_plate)
VALUES (bus_Type, bus_cap,bus_model,bus_color,bus_plate);
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE insert_route(
    IN totalFare INT,
    IN startTime DATETIME,
    IN endTime DATETIME,
    IN origin VARCHAR(64),
    IN dest VARCHAR(64),
    IN driver INT,
    IN bus INT
)
BEGIN
    DECLARE calculatedDuration INT;


    SET calculatedDuration = TIMESTAMPDIFF(HOUR, startTime, endTime);


INSERT INTO bus_route (
    total_fare,
    start_date_time,
    end_date_time,
    origin_city_name,
    destination_city_name,
    driver_id,
    bus_id,
    duration
)
VALUES (
           totalFare,
           startTime,
           endTime,
           origin,
           dest,
           driver,
           bus,
           calculatedDuration
       );
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE view_bus_route()
BEGIN
SELECT route.route_id, route.total_fare, route.duration, route.start_date_time, route.end_date_time
        ,route.origin_city_name, route.destination_city_name, bt.license_plate,
       CONCAT(d.first_name,' ',d.last_name) AS driver_name, d.license_no
FROM bus_route AS route
         INNER JOIN bus_type AS bt ON route.bus_id = bt.bus_id
         INNER JOIN driver AS d ON route.driver_id = d.driver_id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE view_cust_booking()
BEGIN
SELECT bjb.booking_id, br.origin_city_name, br.destination_city_name, br.start_date_time, br.end_date_time, br.duration, bjb.seats_booked, bjb.amount_paid, bjb.payment_method, bt.type, bt.capacity, bt.model, bt.color, d.first_name, d.last_name
FROM bus_journey_booking AS bjb
         INNER JOIN bus_route AS br ON bjb.route_id = br.route_id
         INNER JOIN bus_type AS bt ON br.bus_id = bt.bus_id
         INNER JOIN driver AS d ON br.driver_id = d.driver_id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE bus_booking_chart()
BEGIN
SELECT bt.type AS bus_type, COUNT(bjb.booking_id) AS total_bookings
FROM bus_journey_booking bjb
         JOIN bus_route br ON bjb.route_id = br.route_id
         JOIN bus_type bt ON br.bus_id = bt.bus_id
GROUP BY bt.type;
END //
DELIMITER ;