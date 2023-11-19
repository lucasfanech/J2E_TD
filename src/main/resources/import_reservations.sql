--  Reservation 1
INSERT INTO reservations (id, flight_id, passenger_id)
VALUES (NEXTVAL('reservations_sequence'), 1, 1);

-- Reservation 2
INSERT INTO reservations (id, flight_id, passenger_id)
VALUES (NEXTVAL('reservations_sequence'), 2, 2);
