USE bus_ticket_booking;
DELIMITER //
CREATE TRIGGER duration_update
    BEFORE UPDATE ON bus_route
    FOR EACH ROW
BEGIN
    SET NEW.duration = TIMESTAMPDIFF(HOUR, NEW.start_date_time, NEW.end_date_time);
END;
//
DELIMITER ;
