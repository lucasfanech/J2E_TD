-- Vol 1
INSERT INTO flights (id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES (NEXTVAL('flights_sequence'), 'ABC123', 'ORY/France', 'BOM/India', '2023-11-15', '08:00:00', '2023-11-15', '10:00:00', 1);

-- Vol 2
INSERT INTO flights (id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES (NEXTVAL('flights_sequence'), 'XYZ456', 'ORY/France', 'NYC/USA', '2023-11-16', '12:00:00', '2023-11-16', '14:00:00', 2);
