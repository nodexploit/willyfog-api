-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema willyfog_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema willyfog_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `willyfog_db` DEFAULT CHARACTER SET utf8 ;
USE `willyfog_db` ;

-- -----------------------------------------------------
-- Table `willyfog_db`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`students` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`students` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nif` VARCHAR(12) NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `surname` VARCHAR(60) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `DNI_UNIQUE` (`nif` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`admins` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`admins` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `digest` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`email` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`coordinator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`coordinators` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`coordinators` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`recognizer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`recognizers` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`recognizers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`professors` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`professors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nif` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `coordinator_id` BIGINT NULL,
  `recognizer_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nif_UNIQUE` (`nif` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_professor_coordinator1_idx` (`coordinator_id` ASC),
  UNIQUE INDEX `coordinator_id_UNIQUE` (`coordinator_id` ASC),
  INDEX `fk_professor_recognizer1_idx` (`recognizer_id` ASC),
  UNIQUE INDEX `recognizer_id_UNIQUE` (`recognizer_id` ASC),
  CONSTRAINT `fk_professor_coordinator1`
  FOREIGN KEY (`coordinator_id`)
  REFERENCES `willyfog_db`.`coordinators` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor_recognizer1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`recognizers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`countries` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`countries` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`cities` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`cities` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_city_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `willyfog_db`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`centre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`centres` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`centres` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `city_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_centre_city1_idx` (`city_id` ASC),
  CONSTRAINT `fk_centre_city1`
  FOREIGN KEY (`city_id`)
  REFERENCES `willyfog_db`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`degree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`degrees` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`degrees` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `centre_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Degree_Centre_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Degree_Centre`
  FOREIGN KEY (`centre_id`)
  REFERENCES `willyfog_db`.`centres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`subjects` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`subjects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `degree_id` BIGINT NOT NULL,
  `recognizer_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Subject_Degree1_idx` (`degree_id` ASC),
  INDEX `fk_subject_recognizer1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_Subject_Degree1`
  FOREIGN KEY (`degree_id`)
  REFERENCES `willyfog_db`.`degrees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_recognizer1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`recognizers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`coordinator_coord_centre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`coordinator_coord_centre` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`coordinator_coord_centre` (
  `coordinator_id` BIGINT NOT NULL,
  `centre_id` BIGINT NOT NULL,
  PRIMARY KEY (`coordinator_id`, `centre_id`),
  INDEX `fk_Centre_has_Coordinator_Coordinator1_idx` (`coordinator_id` ASC),
  INDEX `fk_coordinator_coord_centre_centre1_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Centre_has_Coordinator_Coordinator1`
  FOREIGN KEY (`coordinator_id`)
  REFERENCES `willyfog_db`.`coordinators` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coordinator_coord_centre_centre1`
  FOREIGN KEY (`centre_id`)
  REFERENCES `willyfog_db`.`centres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`requests` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`requests` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `recognizer_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_request_student1_idx` (`student_id` ASC),
  INDEX `fk_request_recognizer1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_request_student1`
  FOREIGN KEY (`student_id`)
  REFERENCES `willyfog_db`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_recognizer1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`recognizers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`student_enrolled_degree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`student_enrolled_degree` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`student_enrolled_degree` (
  `student_id` BIGINT NOT NULL,
  `degree_id` BIGINT NOT NULL,
  PRIMARY KEY (`student_id`, `degree_id`),
  INDEX `fk_degree_has_student_student1_idx` (`student_id` ASC),
  INDEX `fk_student_enrolled_degree_degree1_idx` (`degree_id` ASC),
  CONSTRAINT `fk_degree_has_student_student1`
  FOREIGN KEY (`student_id`)
  REFERENCES `willyfog_db`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_enrolled_degree_degree1`
  FOREIGN KEY (`degree_id`)
  REFERENCES `willyfog_db`.`degrees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`subject_equivalent_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`subject_equivalent_subject` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`subject_equivalent_subject` (
  `subject_id` BIGINT NOT NULL,
  `subject_id_eq` BIGINT NOT NULL,
  PRIMARY KEY (`subject_id`, `subject_id_eq`),
  INDEX `fk_subject_has_subject_subject2_idx` (`subject_id_eq` ASC),
  INDEX `fk_subject_has_subject_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_subject_has_subject_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_subject_subject2`
  FOREIGN KEY (`subject_id_eq`)
  REFERENCES `willyfog_db`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`equivalences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `willyfog_db`.`equivalences` ;

CREATE TABLE IF NOT EXISTS `willyfog_db`.`equivalences` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `subject_id` BIGINT NOT NULL,
  `subject_id_eq` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `subject_id`, `subject_id_eq`),
  INDEX `fk_table1_subject1_idx` (`subject_id` ASC),
  INDEX `fk_table1_subject2_idx` (`subject_id_eq` ASC),
  CONSTRAINT `fk_table1_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_subject2`
  FOREIGN KEY (`subject_id_eq`)
  REFERENCES `willyfog_db`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

/*
 * OAuth2 - OpenID schema
 */

CREATE TABLE oauth_clients (
  client_id VARCHAR(80) NOT NULL,
  client_secret VARCHAR(80),
  redirect_uri VARCHAR(2000) NOT NULL,
  grant_types VARCHAR(80),
  scope VARCHAR(100), user_id VARCHAR(80),
  CONSTRAINT clients_client_id_pk
  PRIMARY KEY (client_id)
);

CREATE TABLE oauth_access_tokens (
  access_token VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id VARCHAR(255),
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  CONSTRAINT access_token_pk
  PRIMARY KEY (access_token)
);

CREATE TABLE oauth_authorization_codes (
  authorization_code VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id VARCHAR(255),
  redirect_uri VARCHAR(2000),
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  id_token VARCHAR(2000),
  CONSTRAINT auth_code_pk
  PRIMARY KEY (authorization_code)
);

CREATE TABLE oauth_refresh_tokens (
  refresh_token VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id VARCHAR(255),
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  CONSTRAINT refresh_token_pk
  PRIMARY KEY (refresh_token)
);

CREATE TABLE oauth_users (
  username VARCHAR(255) NOT NULL,
  password VARCHAR(2000),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  CONSTRAINT username_pk
  PRIMARY KEY (username)
);

CREATE TABLE oauth_scopes (
  scope TEXT,
  is_default BOOLEAN
);

CREATE TABLE oauth_jwt (
  client_id VARCHAR(80) NOT NULL,
  subject VARCHAR(80),
  public_key VARCHAR(2000),
  CONSTRAINT jwt_client_id_pk
  PRIMARY KEY (client_id)
);

INSERT INTO oauth_clients (client_id, redirect_uri, grant_types)
VALUES ('testclient', 'http://192.168.33.10/login/callback', 'authorization_code');
INSERT INTO oauth_users (username, password, first_name, last_name)
VALUES ('willy', '$2y$10$5uzVJxZAXMdqDMuSMPRB4.VH1MvYtrOlzJqHLTQyLURkSO0MLRMt.', 'Willy', 'Fog')

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
