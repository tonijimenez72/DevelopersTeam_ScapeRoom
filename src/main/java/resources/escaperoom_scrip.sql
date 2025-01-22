
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
  escape_room_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (escape_room_id) references escape_room (id)
  );
    


CREATE TABLE clue (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  theme ENUM('MYSTERY', 'FANTASY', 'CIFI')  NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  room_id INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (room_id) references room (id)
    
    );



CREATE TABLE decoration (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
 material VARCHAR(255) NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  room_id INT NOT NULL,
  PRIMARY KEY (id),
 
    FOREIGN KEY (room_id) references room (id)
    
    );
    


CREATE TABLE player (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  subscriber TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
  );



CREATE TABLE ticket (
  id INT NOT NULL AUTO_INCREMENT,
  price DOUBLE NULL DEFAULT '0',
  player_id INT NOT NULL,
  room_id INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (player_id) references player (id),
    FOREIGN KEY (room_id) references room (id)
    
    );
    


CREATE TABLE player_has_room (
  player_id INT NOT NULL,
  room_id INT NOT NULL,
    FOREIGN KEY (player_id) references player(id),
    FOREIGN KEY (room_id) references player (id)
    );
    
