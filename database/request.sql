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
(request_id, subject_name, subject_credits, subject_code, centre_name, city_name, university_name, degree_name)
VALUES
(3, 'Asignatura nueva', 4, '4fgh', 'Facultad nueva', 'Málaga', 'Universidad de Málaga', 'Grado de Química');
INSERT INTO `request_destination_subject` (request_id, subject_id) VALUES (3, 5);

-- Role 1 = admin
INSERT INTO `role` (permission) VALUES (0);
-- Role 2 = professor
INSERT INTO `role` (permission) VALUES (0);
-- Role 3 = student
INSERT INTO `role` (permission) VALUES (0);

INSERT INTO `user_has_role` (user_id, role_id) VALUES (1, 3);
INSERT INTO `user_has_role` (user_id, role_id) VALUES (2, 2);
INSERT INTO `user_has_role` (user_id, role_id) VALUES (3, 1);