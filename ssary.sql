DROP SCHEMA IF EXISTS `ssary`;
CREATE SCHEMA IF NOT EXISTS `ssary` DEFAULT CHARACTER SET utf8 ;
USE `ssary` ;

CREATE TABLE IF NOT EXISTS `ssary`.`user` (
  `user_id` INT NOT NULL auto_increment,
  `generate` INT NOT NULL,
  `class` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `birth` DATE,
  `agree` TINYINT DEFAULT 0,
  `permission` TINYINT DEFAULT 0,
  `grade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ssary`.`diary` (
  `diary_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL DEFAULT 'default',
  PRIMARY KEY (`diary_id`),
  CONSTRAINT `diary_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssary`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ssary`.`todo` (
  `todo_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `complete` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`todo_id`),
  CONSTRAINT `todo_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssary`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `ssary`.`recruitment` (
  `recruit_id` INT NOT NULL,
  `task` VARCHAR(45) NULL,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `link` VARCHAR(100) NULL,
  PRIMARY KEY (`recruit_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ssary`.`like` (
  `like_id` INT NOT NULL,
  `recruit_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`like_id`),
  CONSTRAINT `like_recruit`
    FOREIGN KEY (`recruit_id`)
    REFERENCES `ssary`.`recruitment` (`recruit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `like_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssary`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

use ssary;
INSERT INTO `ssary`.`user` (`user_id`, `generate`, `class`, `name`, `email`, `birth`, `password`, `agree`, `permission`, `grade`) VALUES
(1, 12, 1, 'admin', 'admin@test.com', '1990-01-01', 'admin', 1, 1, 'admin'),
(2, 12, 2, 'User1', 'test2@test.com', '1991-02-02', '1234', 1, 0, 'basic'),
(3, 12, 4, 'User2', 'test3@test.com', '1992-03-03', '1234', 1, 0, 'basic'),
(4, 12, 3, 'User3', 'test4@test.com', '1993-04-04', '1234', 1, 0, 'basic'),
(5, 12, 7, 'User4', 'test5@test.com', '1994-05-05', '1234', 1, 0, 'basic'),
(6, 12, 9, 'User5', 'test6@test.com', '1995-06-06', '1234', 1, 0, 'basic'),
(7, 12, 10, 'User6', 'test7@test.com', '1996-07-07', '1234', 1, 0, 'basic'),
(8, 12, 12, 'User7', 'test8@test.com', '1997-08-08', '1234', 1, 0, 'basic'),
(9, 12, 6, 'User8', 'test9@test.com', '1998-09-09', '1234', 1, 0, 'basic'),
(10, 12, 6, 'User9', 'test10@test.com', '1999-10-10', '1234', 1, 1, 'basic');

USE ssary;
SELECT * FROM USER;