/*
 * REQUEST
 */
INSERT INTO `request` (student_id, origin_subject_id, mobility_type_id) VALUES (1, 1, 1);
INSERT INTO `request` (student_id, origin_subject_id, mobility_type_id) VALUES (1, 2, 1);
INSERT INTO `request` (student_id, origin_subject_id, mobility_type_id) VALUES (1, 4, 1);

/*
 * DESTINATION SUBJECT
 */
INSERT INTO `request_destination_subject` (request_id, subject_id) VALUES (1, 2);
INSERT INTO `request_destination_subject` (request_id, subject_id) VALUES (2, 3);
INSERT INTO `request_destination_subject` (request_id, subject_id) VALUES (2, 4);
INSERT INTO `request_destination_subject`
(request_id, subject_name, subject_credits, subject_code, centre_name, city_id)
VALUES
(3, 'Asignatura nueva', 4, '4fgh', 'Facultad nueva', 3);
INSERT INTO `request_destination_subject` (request_id, subject_id) VALUES (3, 5);

