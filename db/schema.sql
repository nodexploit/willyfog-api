-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
USE willyfog_db;
-- -----------------------------------------------------
-- Table `student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student` (
  `id` INT NOT NULL,
  `nif` VARCHAR(12) NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `surname` VARCHAR(60) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `DNI_UNIQUE` (`nif` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admins` (
  `id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `digest` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coordinator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coordinator` (
  `id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recognizer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recognizer` (
  `id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `professor` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nif` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `coordinator_id` INT NULL,
  `recognizer_id` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nif_UNIQUE` (`nif` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_professor_coordinator1_idx` (`coordinator_id` ASC),
  UNIQUE INDEX `coordinator_id_UNIQUE` (`coordinator_id` ASC),
  INDEX `fk_professor_recognizer1_idx` (`recognizer_id` ASC),
  UNIQUE INDEX `recognizer_id_UNIQUE` (`recognizer_id` ASC),
  CONSTRAINT `fk_professor_coordinator1`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `coordinator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor_recognizer1`
    FOREIGN KEY (`recognizer_id`)
    REFERENCES `recognizer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `city` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_city_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `centre` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `city_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_centre_city1_idx` (`city_id` ASC),
  CONSTRAINT `fk_centre_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `degree` (
  `id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Degree_Centre_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Degree_Centre`
    FOREIGN KEY (`centre_id`)
    REFERENCES `centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `subject` (
  `id` INT NOT NULL,
  `degree_id` INT NOT NULL,
  `recognizer_id` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Subject_Degree1_idx` (`degree_id` ASC),
  INDEX `fk_subject_recognizer1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_Subject_Degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_recognizer1`
    FOREIGN KEY (`recognizer_id`)
    REFERENCES `recognizer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coordinator_coord_centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coordinator_coord_centre` (
  `coordinator_id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`coordinator_id`, `centre_id`),
  INDEX `fk_Centre_has_Coordinator_Coordinator1_idx` (`coordinator_id` ASC),
  INDEX `fk_coordinator_coord_centre_centre1_idx` (`centre_id` ASC),
  CONSTRAINT `fk_Centre_has_Coordinator_Coordinator1`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `coordinator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coordinator_coord_centre_centre1`
    FOREIGN KEY (`centre_id`)
    REFERENCES `centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `request` (
  `id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `recognizer_id` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_request_student1_idx` (`student_id` ASC),
  INDEX `fk_request_recognizer1_idx` (`recognizer_id` ASC),
  CONSTRAINT `fk_request_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_recognizer1`
    FOREIGN KEY (`recognizer_id`)
    REFERENCES `recognizer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_enrolled_degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_enrolled_degree` (
  `student_id` INT NOT NULL,
  `degree_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`student_id`, `degree_id`),
  INDEX `fk_degree_has_student_student1_idx` (`student_id` ASC),
  INDEX `fk_student_enrolled_degree_degree1_idx` (`degree_id` ASC),
  CONSTRAINT `fk_degree_has_student_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_enrolled_degree_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `access_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `access_token` (
  `id` INT NOT NULL,
  `access_token` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `access_token_UNIQUE` (`access_token` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `log_grant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `log_grant` (
  `id` INT NOT NULL,
  `admin_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_log_grants_admin1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_log_grants_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES admins (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `log_grant_permitted_centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `log_grant_permitted_centre` (
  `log_grant_id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`log_grant_id`, `centre_id`),
  INDEX `fk_centre_has_log_grants_log_grants1_idx` (`log_grant_id` ASC),
  INDEX `fk_log_grant_permitted_centre_centre1_idx` (`centre_id` ASC),
  CONSTRAINT `fk_centre_has_log_grants_log_grants1`
    FOREIGN KEY (`log_grant_id`)
    REFERENCES `log_grant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_grant_permitted_centre_centre1`
    FOREIGN KEY (`centre_id`)
    REFERENCES `centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `access_token_grants_centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `access_token_grants_centre` (
  `access_token_id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`centre_id`, `access_token_id`),
  INDEX `fk_access_token_has_centre_access_token1_idx` (`access_token_id` ASC),
  INDEX `fk_access_token_grants_centre_centre1_idx` (`centre_id` ASC),
  CONSTRAINT `fk_access_token_has_centre_access_token1`
    FOREIGN KEY (`access_token_id`)
    REFERENCES `access_token` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_access_token_grants_centre_centre1`
    FOREIGN KEY (`centre_id`)
    REFERENCES `centre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `subject_equivalent_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `subject_equivalent_subject` (
  `subject_id` INT NOT NULL,
  `subject_id_eq` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`subject_id`, `subject_id_eq`),
  INDEX `fk_subject_has_subject_subject2_idx` (`subject_id_eq` ASC),
  INDEX `fk_subject_has_subject_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_subject_has_subject_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_subject_subject2`
    FOREIGN KEY (`subject_id_eq`)
    REFERENCES `subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equivalences`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equivalences` (
  `id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `subject_id_eq` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`, `subject_id`, `subject_id_eq`),
  INDEX `fk_table1_subject1_idx` (`subject_id` ASC),
  INDEX `fk_table1_subject2_idx` (`subject_id_eq` ASC),
  CONSTRAINT `fk_table1_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_subject2`
    FOREIGN KEY (`subject_id_eq`)
    REFERENCES `subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `level` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `level_UNIQUE` (`level` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login` (
  `id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `admin_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `professor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_login_role1_idx` (`role_id` ASC),
  INDEX `fk_login_admin1_idx` (`admin_id` ASC),
  INDEX `fk_login_student1_idx` (`student_id` ASC),
  INDEX `fk_login_professor1_idx` (`professor_id` ASC),
  CONSTRAINT `fk_login_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES admins (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_professor1`
    FOREIGN KEY (`professor_id`)
    REFERENCES `professor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
