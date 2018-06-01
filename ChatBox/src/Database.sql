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
  `avatar` BLOB NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  PRIMARY KEY (`login`, `email`),
  INDEX `candidate` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`friends` (
  `user_id` INT UNSIGNED NOT NULL,
  `friend_id` INT UNSIGNED NOT NULL,
  `blocked` ENUM('true', 'false') NULL,
  `date_request` DATETIME NULL,
  `date_accept` DATETIME NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_friend_friend_idx` (`friend_id` ASC),
  CONSTRAINT `fk_user_friend`
    FOREIGN KEY (`user_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_friend`
    FOREIGN KEY (`friend_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  PRIMARY KEY (`idgroups`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`rooms` (
  `idrooms` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT(256) NOT NULL,
  `public` ENUM('true', 'false') NULL,
  `group` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idrooms`),
  INDEX `fk_room_group_idx` (`group` ASC),
  CONSTRAINT `fk_room_group`
    FOREIGN KEY (`group`)
    REFERENCES `chatBox`.`groups` (`idgroups`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatBox`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatBox`.`messages` (
  `idmessages` INT UNSIGNED NOT NULL,
  `room` INT UNSIGNED NULL,
  `content` TEXT(512) NOT NULL,
  `attachment` VARCHAR(255) NULL,
  `dest_id` INT UNSIGNED NULL,
  `date_sent` DATETIME NOT NULL,
  `date_read` DATETIME NULL,
  PRIMARY KEY (`idmessages`),
  INDEX `fk_user_dest_idx` (`dest_id` ASC),
  INDEX `fk_room_message_idx` (`room` ASC),
  CONSTRAINT `fk_user_dest`
    FOREIGN KEY (`dest_id`)
    REFERENCES `chatBox`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_message`
    FOREIGN KEY (`room`)
    REFERENCES `chatBox`.`rooms` (`idrooms`)
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
