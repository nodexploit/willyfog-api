/*
 * COUNTRY
 */
INSERT INTO country (id, name) VALUES (1, 'España');

/*
 * CITIES
 */
INSERT INTO city (id, country_id, name) VALUES (1, 1, 'Málaga');

/*
 * CENTRE
 */
INSERT INTO centre (id, city_id, name) VALUES (1, 1, 'Universidad de Málaga');

/*
 * DEGREE
 */
INSERT INTO degree (id, centre_id, name) VALUES (1, 1, 'Ingeniería Informática');
INSERT INTO degree (id, centre_id, name) VALUES (2, 1, 'Matemáticas');

/**
 * Recognizer
 */
INSERT INTO willyfog_db.user (name, surname, digest, nif, email)
VALUES ('Morcilla', 'Fog', '$2y$10$5uzVJxZAXMdqDMuSMPRB4.VH1MvYtrOlzJqHLTQyLURkSO0MLRMt.', '1111411H', 'a@a.a');

/**
 * SUBJECTS
 */
INSERT INTO `subject` (name, degree_id, recognizer_id) VALUES ('Calculo', 1, 2);
INSERT INTO `subject` (name, degree_id, recognizer_id) VALUES ('Ingles', 2, 2);

/**
 * EQUIVALENCES
 */
INSERT INTO equivalence (subject_id, subject_id_eq) VALUES (1, 2);