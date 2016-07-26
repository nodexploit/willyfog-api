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
-- Table `willyfog_db`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`country` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`city` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_city_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_city_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `willyfog_db`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`centre` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `city_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_centre_city1_idx` (`city_id` ASC),
  CONSTRAINT `fk_centre_city1`
  FOREIGN KEY (`city_id`)
  REFERENCES `willyfog_db`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`degree` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `centre_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_Degree_Centre_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Degree_Centre`
  FOREIGN KEY (`centre_id`)
  REFERENCES `willyfog_db`.`centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nif` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `digest` VARCHAR(100) NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`subject` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `degree_id` BIGINT NOT NULL,
  `recognizer_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_Subject_Degree1_idx` (`degree_id` ASC),
  INDEX `fk_subject_user1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_Subject_Degree1`
  FOREIGN KEY (`degree_id`)
  REFERENCES `willyfog_db`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_user1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`request` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `recognizer_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_request_user1_idx` (`user_id` ASC),
  INDEX `fk_request_user2_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_request_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_user2`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`subject_equivalent_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`subject_equivalent_subject` (
  `subject_id` BIGINT NOT NULL,
  `subject_id_eq` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`subject_id`, `subject_id_eq`),
  INDEX `fk_subject_has_subject_subject2_idx` (`subject_id_eq` ASC),
  INDEX `fk_subject_has_subject_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_subject_has_subject_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_subject_subject2`
  FOREIGN KEY (`subject_id_eq`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`equivalence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`equivalence` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `subject_id` BIGINT NOT NULL,
  `subject_id_eq` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `subject_id`, `subject_id_eq`),
  INDEX `fk_table1_subject1_idx` (`subject_id` ASC),
  INDEX `fk_table1_subject2_idx` (`subject_id_eq` ASC),
  CONSTRAINT `fk_table1_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_subject2`
  FOREIGN KEY (`subject_id_eq`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `permission` INT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user_enrolled_degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user_enrolled_degree` (
  `user_id` BIGINT NOT NULL,
  `degree_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `degree_id`),
  INDEX `fk_user_has_degree_degree1_idx` (`degree_id` ASC),
  INDEX `fk_user_has_degree_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_degree_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_degree_degree1`
  FOREIGN KEY (`degree_id`)
  REFERENCES `willyfog_db`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user_has_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_role_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `willyfog_db`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user_coord_centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user_coord_centre` (
  `user_id` BIGINT NOT NULL,
  `centre_id` BIGINT NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `centre_id`),
  INDEX `fk_user_has_centre_centre1_idx` (`centre_id` ASC),
  INDEX `fk_user_has_centre_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_centre_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_centre_centre1`
  FOREIGN KEY (`centre_id`)
  REFERENCES `willyfog_db`.`centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bit` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `deleted_at` TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;

/*
 * OAuth2 - OpenID schema
 */

CREATE TABLE willyfog_db.oauth_client (
  client_id VARCHAR(80) NOT NULL,
  client_secret VARCHAR(80),
  redirect_uri VARCHAR(2000) NOT NULL,
  grant_types VARCHAR(80),
  scope VARCHAR(100), user_id VARCHAR(80),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT clients_client_id_pk
  PRIMARY KEY (client_id)
);

CREATE TABLE willyfog_db.oauth_access_token (
  access_token VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id BIGINT,
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT access_token_pk
  PRIMARY KEY (access_token),
  INDEX `fk_oauth_access_token_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_oauth_access_token_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE willyfog_db.oauth_authorization_code (
  authorization_code VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id BIGINT,
  redirect_uri VARCHAR(2000),
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  id_token VARCHAR(2000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT auth_code_pk
  PRIMARY KEY (authorization_code),
  INDEX `fk_oauth_authorization_code_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_oauth_authorization_code_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE willyfog_db.oauth_refresh_token (
  refresh_token VARCHAR(40) NOT NULL,
  client_id VARCHAR(80) NOT NULL,
  user_id BIGINT,
  expires TIMESTAMP NOT NULL,
  scope VARCHAR(2000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT refresh_token_pk
  PRIMARY KEY (refresh_token),
  INDEX `fk_oauth_refresh_token_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_oauth_refresh_token_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE willyfog_db.oauth_scope (
  scope TEXT,
  is_default BOOLEAN,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE willyfog_db.oauth_jwt (
  client_id VARCHAR(80) NOT NULL,
  subject VARCHAR(80),
  public_key VARCHAR(2000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT jwt_client_id_pk
  PRIMARY KEY (client_id)
);

INSERT INTO willyfog_db.oauth_client (client_id, redirect_uri, grant_types)
VALUES ('testclient', 'http://willyfog.com/login/callback', 'authorization_code');
INSERT INTO willyfog_db.oauth_client (client_id, client_secret, redirect_uri, grant_types)
VALUES ('webclient', 'websecret', 'http://willyfog.com/login/callback', 'authorization_code');
INSERT INTO willyfog_db.oauth_client (client_id, client_secret, redirect_uri, grant_types)
VALUES ('mobileclient', 'mobilesecret', 'willyfog://login/callback', 'authorization_code');
-- Hashed password: 'foobar'
INSERT INTO willyfog_db.user (name, surname, digest, nif, email)
VALUES ('Willy', 'Fog', '$2y$10$5uzVJxZAXMdqDMuSMPRB4.VH1MvYtrOlzJqHLTQyLURkSO0MLRMt.', '1111111H', 'willy@example.com');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;