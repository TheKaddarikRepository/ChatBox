-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema chatBox
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chatBox
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chatBox` DEFAULT CHARACTER SET utf8 ;
USE `chatBox` ;

-- -----------------------------------------------------
-- Table `chatBox`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`users` (
  `login` VARCHAR(20) NOT NULL,
  `name` VARCHAR(35) NULL,
  `firstname` VARCHAR(35) NULL,
  `email` VARCHAR(254) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `avatar` VARCHAR(254) NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  PRIMARY KEY (`login`, `email`),
  INDEX `candidate` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`roles` (
  `idroles` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT(256) NULL,
  PRIMARY KEY (`idroles`, `user_id`),
  INDEX `fk_user_role_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`groups` (
  `idgroups` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `admin` INT UNSIGNED NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `end_date` DATETIME NULL,
  `group_type` ENUM('PUBLIC', 'PRIVATE', 'FRIEND') NULL,
  PRIMARY KEY (`idgroups`),
  INDEX `admin_idx` (`admin` ASC),
  CONSTRAINT `admin`
    FOREIGN KEY (`admin`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`messages` (
  `idmessages` INT UNSIGNED NOT NULL,
  `group_id` INT UNSIGNED NULL,
  `content` TEXT(512) NOT NULL,
  `attachment` VARCHAR(255) NULL,
  `date_sent` DATETIME NOT NULL,
  `author_id` INT UNSIGNED NULL,
  `type` ENUM('INFO', 'REQUEST', 'ANSWER') NULL,
  PRIMARY KEY (`idmessages`),
  INDEX `fk_author_user_idx` (`author_id` ASC),
  INDEX `fk_group_message_idx` (`group_id` ASC),
  CONSTRAINT `fk_author_user`
    FOREIGN KEY (`author_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_message`
    FOREIGN KEY (`group_id`)
    REFERENCES `chatBox`.`groups` (`idgroups`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`group_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`group_users` (
  `grp_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `request_date` DATETIME NOT NULL,
  `valid_date` DATETIME NULL,
  `banned_date` DATETIME NULL,
  `last_read` DATETIME NULL,
  PRIMARY KEY (`grp_id`, `user_id`),
  INDEX `fk_user_group_idx` (`user_id` ASC),
  CONSTRAINT `fk_group_group`
    FOREIGN KEY (`grp_id`)
    REFERENCES `chatBox`.`groups` (`idgroups`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_group`
    FOREIGN KEY (`user_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`new_password`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`new_password` (
  `url_new` VARCHAR(256) NOT NULL,
  `id_user` INT UNSIGNED NULL,
  `date_sent` DATETIME NULL,
  `pending` ENUM('true', 'false') NULL,
  `date_expire` DATETIME NULL,
  PRIMARY KEY (`url_new`),
  INDEX `fk_user_password_idx` (`id_user` ASC),
  CONSTRAINT `fk_user_password`
    FOREIGN KEY (`id_user`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
