DROP TABLE IF EXISTS sensor_records;
DROP TABLE IF EXISTS sensors;
DROP TABLE IF EXISTS districts;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS user;

CREATE TABLE cities (
  id INT PRIMARY KEY,
  city_name VARCHAR(250) NOT NULL,
  username  VARCHAR(250) NOT NULL
);

CREATE TABLE districts (
  id INT PRIMARY KEY,
  district_name VARCHAR(250) NOT NULL,
  city_id INT NOT NULL references cities(id)
);

CREATE TABLE sensors (
  id INT PRIMARY KEY,
  district_id INT NOT NULL references districts(id)
);

CREATE TABLE sensor_records (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  co2_level DOUBLE NOT NULL,
  insertion_time TIMESTAMP NOT NULL,
  sensor_id INT NOT NULL references sensors(id)
);

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  city_id INT NOT NULL);

INSERT INTO cities (id, city_name, username) VALUES
(1, 'Barcelona','blona'),
(2, 'Wien','wien'),
(3, 'Muenchen','mncn');

INSERT INTO districts (id, district_name, city_id) VALUES
(1, 'Gracia',1),
(2, 'Eixample',1),
(3, 'Waehring',2),
(4, 'Penzing',2),
(5, 'Maxvorstadt',3);

INSERT INTO sensors (id,district_id) VALUES
(1,1),
(2,3),
(3,5),
(4,4),
(5,4);

INSERT INTO sensor_records (co2_level, insertion_time, sensor_id) VALUES
(20.5,'2017-09-17 18:47:52.69',1),
(13,'2018-09-17 18:47:52.69',3),
(16,'2019-09-17 18:47:52.69',5),
(12,'2019-09-17 18:47:52.69',4),
(1,'2016-09-17 18:47:52.69',4);

INSERT INTO user (id, username, password, city_id) VALUES
(1,'blona', 'test',1),
(2,'wien', 'admin',2),
(3,'mncn', 'admin',3);