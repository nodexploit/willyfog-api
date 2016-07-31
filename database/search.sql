/**
 * City
 */

INSERT INTO `city` (name, country_id) VALUES ('Madrid', 202);
INSERT INTO `city` (name, country_id) VALUES ('Málaga', 202);
INSERT INTO `city` (name, country_id) VALUES ('Barcelona', 202);
INSERT INTO `city` (name, country_id) VALUES ('Valencia', 202);
INSERT INTO `city` (name, country_id) VALUES ('Sevilla', 202);

 /**
 * University
 */

INSERT INTO `university` (name, code, city_id) VALUES ('Universidad Complutense de Madrid', '010', 1);
INSERT INTO `university` (name, code, city_id) VALUES ('Universidad de Málaga', '011', 2);
INSERT INTO `university` (name, code, city_id) VALUES ('Universidad Politécnica de Catalunya', '024', 3);
INSERT INTO `university` (name, code, city_id) VALUES ('Universitat de València (Estudi General)', '018', 4);
INSERT INTO `university` (name, code, city_id) VALUES ('Universidad de Sevilla', '017', 5);


 /**
 * Centre
 */

INSERT INTO `centre` (name, code, university_id) VALUES ('Facultad de Bellas Artes', '28027126', 1);
INSERT INTO `centre` (name, code, university_id) VALUES ('Escuela Técnica Superior de Ingeniería Informática', '29012601', 2);
INSERT INTO `centre` (name, code, university_id) VALUES ('Escuela de Ingeniería de Telecomunicación y Aeroespacial de Castelldefels', '08070027', 3);
INSERT INTO `centre` (name, code, university_id) VALUES ('Departamento de Derecho Administrativo y Procesal', '46062051', 4);
INSERT INTO `centre` (name, code, university_id) VALUES ('Escuela Internacional de Doctorado', '41015858', 5);



/**
 * Degree
 */

INSERT INTO `degree` (centre_id, name) VALUES (1, 'Grado en Bellas Artes');
INSERT INTO `degree` (centre_id, name) VALUES (1, 'Grado en Historia del Arte');
INSERT INTO `degree` (centre_id, name) VALUES (2, 'Grado en Ingeniería Informática');
INSERT INTO `degree` (centre_id, name) VALUES (2, 'Grado en Ingeniería del Software');
INSERT INTO `degree` (centre_id, name) VALUES (3, 'Grado en Telecomunicaciones');
INSERT INTO `degree` (centre_id, name) VALUES (3, 'Grado en Ingeniería Espacial');
INSERT INTO `degree` (centre_id, name) VALUES (4, 'Grado en Derecho');
INSERT INTO `degree` (centre_id, name) VALUES (4, 'Grado en Administración y Dirección de Empresas');
INSERT INTO `degree` (centre_id, name) VALUES (5, 'Grado en Doctorado');
INSERT INTO `degree` (centre_id, name) VALUES (5, 'Grado en Medicina');

/**
 * Subject
 */

INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('T3Rp', 1, 'Historia de España', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('B3Ht', 2, 'Arte Moderno', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('Q2RO', 3, 'Cálculo', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('jvdH', 4, 'Estructuras de Datos', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('SCVg', 5, 'Redes y Sistemas', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('ReyO', 6, 'Física I', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('xz6S', 7, 'Derecho Romano', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('G4TH', 8, 'Microeconomía', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('RvZz', 9, 'Investigación', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('fT4q', 10, 'Anatomía I', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('JZs8', 1, 'Dibujo Técnico', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('mkQH', 2, 'Historia de Grecia', 6);
INSERT INTO `subject` (code, degree_id, name, credits) VALUES ('1Dvw', 3, 'Lógica Computacional', 6);
