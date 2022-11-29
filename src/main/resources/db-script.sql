
CREATE TABLE `user` (
    userName VARCHAR(100) PRIMARY KEY ,
    password VARCHAR(100) NOT NULL ,
    fullName VARCHAR (200) NOT NULL
);

CREATE TABLE `to-do-item`(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    username VARCHAR(100) NOT NULL ,
    description VARCHAR(500) NOT NULL ,
    status ENUM('DONE','NOT_DONE'),
    CONSTRAINT fk_name FOREIGN KEY (username) REFERENCES `user` (userName)
);
