
create database escaperoomdb;
USE escaperoomdb;

CREATE TABLE escape_room (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  is_deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id)
);



CREATE TABLE room (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  theme ENUM('MYSTERY', 'FANTASY', 'CIFI'),
  difficulty_level ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  escape_room_id INT null,
  is_deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (escape_room_id) references escape_room (id)
  );
    


CREATE TABLE clue (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  theme ENUM('MYSTERY', 'FANTASY', 'CIFI')  NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
  room_id int null DEFAULT 0,
  is_deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id),
    FOREIGN KEY (room_id) references room (id)
    
    );



CREATE TABLE decoration (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
 material VARCHAR(255) NOT NULL,
  price DOUBLE NOT NULL,
  available TINYINT(1) NOT NULL DEFAULT '1',
	room_id int  null DEFAULT 0,
  is_deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id),
 
    FOREIGN KEY (room_id) references room (id)
    
    );
    


CREATE TABLE player (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NULL,
  phone int null,
  subscriber tinyint(1) not null default "0",
  created_at datetime default current_timestamp null,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  is_deleted BOOLEAN DEFAULT FALSE,
  
  PRIMARY KEY (id)
  );

    


CREATE TABLE player_has_room (
  player_id INT NOT NULL,
  room_id INT NOT NULL,
    FOREIGN KEY (player_id) references player(id),
    FOREIGN KEY (room_id) references player (id)
    );
    
select* from clue;
DELETE FROM room WHERE id = 1;

select* from room;



ALTER TABLE player ADD COLUMN surname VARCHAR(255) NOT NULL;
ALTER TABLE player ADD COLUMN phone int NOT NULL;
AlTer Table clue modify column room_id int null;
AlTer Table room modify column escape_room_id int null;
AlTer Table decoration modify column room_id int null DEFAULT 0;
AlTer Table clue modify column room_id int null DEFAULT 0;


ALTER TABLE player ADD COLUMN subscriber TINYINT(1) NOT NULL DEFAULT '0';
DROP TABLE ticket;
ALTER TABLE player ADD column created_at datetime default current_timestamp null;
ALTER TABLE player ADD COLUMN updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
AlTer Table player modify column surname VARCHAR(255) NULL;
AlTer Table player modify column phone int NULL;


ALTER TABLE room ADD COLUMN is_deleted TINYINT(1) NOT NULL DEFAULT '0';
select* from room;
ALTER TABLE decoration ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;
ALTER TABLE clue ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;
ALTER TABLE player ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;
ALTER TABLE escape_room ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;

ALTER TABLE room DROP COLUMN description;


select* from room;