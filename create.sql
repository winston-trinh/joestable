-- AUTHOR: WINSTON TRINH, 2024
-- Run this SQL script to initialize tables
-- Run it line by line, chronologically at
-- line 6, 7, 8, 11, 14, 17, 20, 27, 40, 49

DROP DATABASE JoesTable;
CREATE DATABASE IF NOT EXISTS JoesTable;
USE JoesTable;

-- Delete the Favorites table
DROP TABLE `JoesTable`.`Favorites`;

-- Delete the Users table
DROP TABLE `JoesTable`.`Users`;

-- Delete the Restaurants table
DROP TABLE `JoesTable`.`Restaurants`;

-- Create the Users table
CREATE TABLE `JoesTable`.`Users` (
  `email` VARCHAR(255) NOT NULL PRIMARY KEY,
  `username` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NULL
);

-- Create the Restaurants table
CREATE TABLE `JoesTable`.`Restaurants` (
  `restaurant_id` VARCHAR(255) NOT NULL PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `cuisine` VARCHAR(255) NOT NULL,
  `price` VARCHAR(255) NOT NULL,
  `rating` DOUBLE NOT NULL,
  `url` VARCHAR(255),
  `image_url` VARCHAR(255)
);

-- Create the Favorites table with foreign keys to Users and Restaurants
CREATE TABLE `JoesTable`.`Favorites` (
  `fav_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `email` VARCHAR(255) NOT NULL,
  `restaurant_id` VARCHAR(255) NOT NULL,
  FOREIGN KEY (`email`) REFERENCES `Users`(`email`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `Restaurants`(`restaurant_id`)
);

-- Create the Reservations table with foreign keys to Users and Restaurants
CREATE TABLE `JoesTable`.`Reservations` (
  `reservation_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `email` VARCHAR(255) NOT NULL,
  `restaurant_id` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `reservation_notes` TEXT,
  FOREIGN KEY (`email`) REFERENCES `Users`(`email`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `Restaurants`(`restaurant_id`)
);


-- Run this SQL script to clear tables
-- Run it line by line, chronologically at
-- line 66, 69, 72, 75

-- Clear the Favorites table
DELETE FROM `JoesTable`.`Favorites`;

-- Clear the Reservations table
DELETE FROM `JoesTable`.`Reservations`;

-- Clear the Users table
DELETE FROM `JoesTable`.`Users`;

-- Clear the Restaurants table
DELETE FROM `JoesTable`.`Restaurants`;