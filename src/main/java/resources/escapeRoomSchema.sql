
create database escaperoomdb;
USE escaperoomdb;

CREATE TABLE escape_room (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);



CREATE TABLE room (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  theme ENUM('MYSTERY', 'FANTASY', 'CIFI'),
  difficulty_level ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL,
  description TEXT NULL DEFAULT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  escape_room_id INT null,
  PRIMARY KEY (id),
  FOREIGN KEY (escape_room_id) references escape_room (id)
  );
    


CREATE TABLE clue (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  theme ENUM('MYSTERY', 'FANTASY', 'CIFI')  NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  room_id INT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (room_id) references room (id)
    
    );



CREATE TABLE decoration (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
 material VARCHAR(255) NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  room_id INT NULL,
  PRIMARY KEY (id),
 
    FOREIGN KEY (room_id) references room (id)
    
    );
    


CREATE TABLE player (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  phone int NOT NULL,
  subscriber tinyint(1) not null default "0",
  created_at datetime default current_timestamp null,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  
  PRIMARY KEY (id)
  );

    


CREATE TABLE player_has_room (
  player_id INT NOT NULL,
  room_id INT NOT NULL,
    FOREIGN KEY (player_id) references player(id),
    FOREIGN KEY (room_id) references player (id)
    );
    



ALTER TABLE player ADD COLUMN surname VARCHAR(255) NOT NULL;
ALTER TABLE player ADD COLUMN phone int NOT NULL;
AlTer Table clue modify column room_id int null;
AlTer Table room modify column escape_room_id int null;
AlTer Table decoration modify column room_id int null;
ALTER TABLE player ADD COLUMN subscriber TINYINT(1) NOT NULL DEFAULT '0';
DROP TABLE ticket;
ALTER TABLE player ADD column created_at datetime default current_timestamp null;
ALTER TABLE player ADD COLUMN updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;