CREATE DATABASE mytest;

DROP TABLE t_user;
CREATE TABLE t_user(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  phone VARCHAR(255) NOT NULL,
  class_id INT NOT NULL
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE t_classes;
CREATE TABLE t_classes(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  class_name VARCHAR(255) NOT NULL
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;