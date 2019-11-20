/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 5.7.21-log : Database - neverstop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`neverstop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `neverstop`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` varchar(255) NOT NULL,
  `city_name` varchar(25) NOT NULL,
  `country_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKdjnk44fptegbsu6drhc9xvlfj` (`city_name`),
  KEY `city_country_id` (`country_id`),
  CONSTRAINT `city_country_id` FOREIGN KEY (`country_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `city` */

/*Table structure for table `continent` */

DROP TABLE IF EXISTS `continent`;

CREATE TABLE `continent` (
  `id` varchar(255) NOT NULL,
  `continent_name` varchar(25) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfj51n6jbvqc0vst21uu75dbho` (`continent_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `continent` */

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` varchar(255) NOT NULL,
  `continent_id` varchar(255) DEFAULT NULL,
  `country_name` varchar(25) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqrkn270121aljmucrdbnmd35p` (`country_name`),
  KEY `country_continent_fk` (`continent_id`),
  CONSTRAINT `country_continent_fk` FOREIGN KEY (`continent_id`) REFERENCES `continent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `country` */

/*Table structure for table `entity` */

DROP TABLE IF EXISTS `entity`;

CREATE TABLE `entity` (
  `id` varchar(255) NOT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `entity_name` varchar(255) DEFAULT NULL,
  `entity_status` int(11) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longtitude` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `thumb_image` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `entity_user_fk` (`userId`),
  CONSTRAINT `entity_user_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `entity` */

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` varchar(255) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values 
('00bdadc2-692c-47cb-bdad-c2692c97cbd9',1),
('0ee0ac63-3958-487f-a0ac-633958987fb0',2),
('732eb5f3-fecd-41d3-aeb5-f3fecd81d39a',2);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`active`,`device_id`,`email`,`name`,`password`,`register_date`,`username`) values 
('00bdadc2-692c-47cb-bdad-c2692c97cbd9','',NULL,'neverstop.tgi@gmail.com','Admin','$2a$10$Wwzsgnu9PipONtMcY7FD5uAEU.DbksA6aURfHRGjw/IthJgDP9vty','2019-10-12 21:02:11','admin'),
('0ee0ac63-3958-487f-a0ac-633958987fb0','',NULL,'murali@gmail.com','Murali','$2a$10$ZUHCoWj9dICUDR1lHZEt1ekm9DfoPoL4ZlyrG7Gpiy0ALwrGvkXMG','2019-10-12 21:02:39','murali'),
('732eb5f3-fecd-41d3-aeb5-f3fecd81d39a','',NULL,'anand@gmail.com','Anand','$2a$10$UQ4tbvqmmyv.KNNgKhU32OayUa6ir/p8BNDCYnOmIS3cmQ4YoPNOy','2019-10-12 21:04:41','anand');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

ALTER TABLE `neverstop`.`city` 
DROP FOREIGN KEY `city_country_id`;
ALTER TABLE `neverstop`.`city`;

ALTER TABLE `neverstop`.`city` RENAME INDEX `city_country_id` TO `city_country_id_idx`;
ALTER TABLE `neverstop`.`city` ALTER INDEX `city_country_id_idx` VISIBLE;
ALTER TABLE `neverstop`.`city` 
ADD CONSTRAINT `city_country_id`
  FOREIGN KEY (`country_id`)
  REFERENCES `neverstop`.`country` (`id`);

  
 ALTER TABLE `neverstop`.`entity` 
ADD COLUMN `hours` VARCHAR(45) NULL AFTER `description`;

ALTER TABLE `neverstop`.`review` 
RENAME TO  `neverstop`.`reviews` ;


ALTER TABLE `neverstop`.`review` 
CHANGE COLUMN `like` `is_like` INT(11) NULL DEFAULT NULL ;

ALTER TABLE `neverstop`.`city` 
ADD COLUMN `city_img` VARCHAR(45) NULL AFTER `city_name`;



ALTER TABLE `neverstop`.`users` 
DROP COLUMN `email`,
DROP INDEX `UK6dotkott2kjsp8vw4d0m25fb7` ;
;


ALTER TABLE `neverstop`.`continent` 
ADD COLUMN `continent_image` VARCHAR(100) NULL AFTER `status`;


ALTER TABLE `neverstop`.`country` 
ADD COLUMN `country_image` VARCHAR(100) NULL AFTER `status`;

ALTER TABLE `neverstop`.`state` 
ADD COLUMN `state_image` VARCHAR(100) NULL AFTER `status`;

