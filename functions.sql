USE bus_ticket_booking;

DELIMITER //
CREATE FUNCTION getCountOfDriver(driverId INT) RETURNS INT
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM driver
WHERE driver_id = driverId;

RETURN countResult;
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION getCountOfCity(cityName varchar(64)) RETURNS INT
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM city
WHERE city_name = cityName;

RETURN countResult ;
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION getCountOfRoute(routeId INT) RETURNS INT
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM bus_route
WHERE route_id = routeId;

RETURN countResult ;
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION getCountOfBus(busId INT) RETURNS INT
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM bus_type
WHERE bus_id = busId;

RETURN countResult ;
END //
DELIMITER ;


