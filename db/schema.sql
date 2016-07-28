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
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`city` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `city_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `name` VARCHAR(120) NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_Degree_Centre_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Degree_Centre`
  FOREIGN KEY (`centre_id`)
  REFERENCES `willyfog_db`.`centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`subject` (
  `id` INT NOT NULL,
  `degree_id` INT NOT NULL,
  `name` VARCHAR(120) NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_Subject_Degree1_idx` (`degree_id` ASC),
  CONSTRAINT `fk_Subject_Degree1`
  FOREIGN KEY (`degree_id`)
  REFERENCES `willyfog_db`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nif` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `digest` VARCHAR(100) NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `subject_eq_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_request_user1_idx` (`student_id` ASC),
  INDEX `fk_request_subject1_idx` (`subject_id` ASC),
  INDEX `fk_request_subject2_idx` (`subject_eq_id` ASC),
  CONSTRAINT `fk_request_user1`
  FOREIGN KEY (`student_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_subject2`
  FOREIGN KEY (`subject_eq_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`likely_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`likely_subject` (
  `id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `subject_eq_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `subject_id`, `subject_eq_id`),
  INDEX `fk_subject_has_subject_subject2_idx` (`subject_eq_id` ASC),
  INDEX `fk_subject_has_subject_subject1_idx` (`subject_id` ASC),
  UNIQUE INDEX `unique_subject_likely` (`subject_id` ASC, `subject_eq_id` ASC),
  CONSTRAINT `fk_subject_has_subject_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_subject_subject2`
  FOREIGN KEY (`subject_eq_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`equivalence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`equivalence` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject_id` INT NOT NULL,
  `subject_eq_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `subject_id`, `subject_eq_id`),
  INDEX `fk_table1_subject1_idx` (`subject_id` ASC),
  INDEX `fk_table1_subject2_idx` (`subject_eq_id` ASC),
  UNIQUE INDEX `unique_subject_equivalent` (`subject_id` ASC, `subject_eq_id` ASC),
  CONSTRAINT `fk_table1_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_subject2`
  FOREIGN KEY (`subject_eq_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`role` (
  `id` INT NOT NULL,
  `permission` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user_enrolled_degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user_enrolled_degree` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `degree_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `user_id`, `degree_id`),
  INDEX `fk_user_has_degree_degree1_idx` (`degree_id` ASC),
  INDEX `fk_user_has_degree_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `unique_subject_likely` (`user_id` ASC, `degree_id` ASC),
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
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `unique_user_role` (`user_id` ASC, `role_id` ASC),
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
-- Table `willyfog_db`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`permission` (
  `id` INT NOT NULL,
  `bit` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`user_recognize_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`user_recognize_subject` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `user_id`, `subject_id`),
  INDEX `fk_user_has_subject_subject1_idx` (`subject_id` ASC),
  INDEX `fk_user_has_subject_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `unique_user_subject` (`user_id` ASC, `subject_id` ASC),
  CONSTRAINT `fk_user_has_subject_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_subject_subject1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `willyfog_db`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `request_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_request1_idx` (`request_id` ASC),
  CONSTRAINT `fk_comment_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `willyfog_db`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`accepted_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`accepted_request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `request_id` INT NOT NULL,
  `recognizer_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_accepted_request_request1_idx` (`request_id` ASC),
  INDEX `fk_accepted_request_user1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_accepted_request_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `willyfog_db`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accepted_request_user1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`rejected_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`rejected_request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `request_id` INT NOT NULL,
  `recognizer_id` INT NOT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_rejected_request_request1_idx` (`request_id` ASC),
  INDEX `fk_rejected_request_user1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_rejected_request_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `willyfog_db`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rejected_request_user1`
  FOREIGN KEY (`recognizer_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `readed_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_notification_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_notification_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_client` (
  `client_id` VARCHAR(80) NOT NULL,
  `client_secret` VARCHAR(80) NULL,
  `redirect_uri` VARCHAR(2000) NULL,
  `grant_types` VARCHAR(80) NULL,
  `scope` VARCHAR(100) NULL,
  `user_id` INT NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `client_id_UNIQUE` (`client_id` ASC),
  UNIQUE INDEX `client_id_secret_UNQIUE` (`client_id` ASC, `client_secret` ASC),
  INDEX `fk_oauth_client_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_oauth_client_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_access_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_access_token` (
  `access_token` VARCHAR(40) NOT NULL,
  `user_id` INT NULL,
  `client_id` VARCHAR(80) NOT NULL,
  `expires` TIMESTAMP NOT NULL,
  `scope` VARCHAR(2000) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`access_token`),
  INDEX `fk_oauth_access_token_user1_idx` (`user_id` ASC),
  INDEX `fk_oauth_access_token_oauth_client1_idx` (`client_id` ASC),
  CONSTRAINT `fk_oauth_access_token_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oauth_access_token_oauth_client1`
  FOREIGN KEY (`client_id`)
  REFERENCES `willyfog_db`.`oauth_client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_authorization_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_authorization_code` (
  `authorization_code` VARCHAR(40) NOT NULL,
  `user_id` INT NULL,
  `client_id` VARCHAR(80) NOT NULL,
  `redirect_uri` VARCHAR(2000) NULL,
  `expires` TIMESTAMP NOT NULL,
  `scope` VARCHAR(2000) NULL,
  `id_token` VARCHAR(2000) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`authorization_code`),
  UNIQUE INDEX `client_id_UNIQUE` (`authorization_code` ASC),
  UNIQUE INDEX `client_id_secret_UNQIUE` (`authorization_code` ASC),
  INDEX `fk_oauth_client_user1_idx` (`user_id` ASC),
  INDEX `fk_oauth_authorization_code_oauth_client1_idx` (`client_id` ASC),
  CONSTRAINT `fk_oauth_client_user10`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oauth_authorization_code_oauth_client1`
  FOREIGN KEY (`client_id`)
  REFERENCES `willyfog_db`.`oauth_client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_jwt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_jwt` (
  `client_id` VARCHAR(80) NOT NULL,
  `subject` VARCHAR(80) NULL,
  `public_key` VARCHAR(2000) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_oauth_jwt_oauth_client1_idx` (`client_id` ASC),
  CONSTRAINT `fk_oauth_jwt_oauth_client1`
  FOREIGN KEY (`client_id`)
  REFERENCES `willyfog_db`.`oauth_client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_scope`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_scope` (
  `scope` VARCHAR(50) NOT NULL,
  `is_default` TINYINT(1) NULL,
  `deleted_at` TIMESTAMP NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`scope`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `willyfog_db`.`oauth_refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `willyfog_db`.`oauth_refresh_token` (
  `refresh_token` VARCHAR(40) NOT NULL,
  `user_id` INT NULL,
  `client_id` VARCHAR(80) NOT NULL,
  `expires` TIMESTAMP NOT NULL,
  `scope` VARCHAR(2000) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`refresh_token`),
  INDEX `fk_oauth_refresh_token_user1_idx` (`user_id` ASC),
  INDEX `fk_oauth_refresh_token_oauth_client1_idx` (`client_id` ASC),
  CONSTRAINT `fk_oauth_refresh_token_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `willyfog_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oauth_refresh_token_oauth_client1`
  FOREIGN KEY (`client_id`)
  REFERENCES `willyfog_db`.`oauth_client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `willyfog_db`.`country`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`country` (`id`, `name`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 'Espana', NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`city`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`city` (`id`, `name`, `country_id`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 'Malaga', 1, NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`centre`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`centre` (`id`, `name`, `city_id`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 'Universidad de Malaga', 1, NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`degree`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`degree` (`id`, `centre_id`, `name`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 1, 'Ingeniera Informatica', NULL, DEFAULT, DEFAULT);
INSERT INTO `willyfog_db`.`degree` (`id`, `centre_id`, `name`, `deleted_at`, `created_at`, `updated_at`) VALUES (2, 1, 'Matematicas', NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`subject`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`subject` (`id`, `degree_id`, `name`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 1, 'Calculo', NULL, DEFAULT, DEFAULT);
INSERT INTO `willyfog_db`.`subject` (`id`, `degree_id`, `name`, `deleted_at`, `created_at`, `updated_at`) VALUES (2, 2, 'Ingles', NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`user` (`id`, `name`, `surname`, `nif`, `email`, `digest`, `deleted_at`, `created_at`, `updated_at`) VALUES (2, 'Morcilla', 'Reconocedor', '1111411H', 'a@a.a', '$2y$10$5uzVJxZAXMdqDMuSMPRB4.VH1MvYtrOlzJqHLTQyLURkSO0MLRMt.', NULL, DEFAULT, DEFAULT);
INSERT INTO `willyfog_db`.`user` (`id`, `name`, `surname`, `nif`, `email`, `digest`, `deleted_at`, `created_at`, `updated_at`) VALUES (1, 'Willy', 'Fog', '1111111H', 'willy@example.com', '$2y$10$5uzVJxZAXMdqDMuSMPRB4.VH1MvYtrOlzJqHLTQyLURkSO0MLRMt.', NULL, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`equivalence`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`equivalence` (`id`, `subject_id`, `subject_eq_id`, `deleted_at`, `created_at`) VALUES (1, 1, 2, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`user_recognize_subject`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`user_recognize_subject` (`id`, `user_id`, `subject_id`, `deleted_at`, `created_at`) VALUES (1, 2, 1, NULL, DEFAULT);
INSERT INTO `willyfog_db`.`user_recognize_subject` (`id`, `user_id`, `subject_id`, `deleted_at`, `created_at`) VALUES (1, 2, 2, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `willyfog_db`.`oauth_client`
-- -----------------------------------------------------
START TRANSACTION;
USE `willyfog_db`;
INSERT INTO `willyfog_db`.`oauth_client` (`client_id`, `client_secret`, `redirect_uri`, `grant_types`, `scope`, `user_id`, `deleted_at`, `created_at`, `updated_at`) VALUES ('webclient', 'websecret', 'http://willyfog.com/login/callback', 'authorization_code', NULL, NULL, NULL, DEFAULT, DEFAULT);
INSERT INTO `willyfog_db`.`oauth_client` (`client_id`, `client_secret`, `redirect_uri`, `grant_types`, `scope`, `user_id`, `deleted_at`, `created_at`, `updated_at`) VALUES ('mobileclient', 'mobilesecret', 'willyfog://login/callback', 'authorization_code', NULL, NULL, NULL, DEFAULT, DEFAULT);

COMMIT;