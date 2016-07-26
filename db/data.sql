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
