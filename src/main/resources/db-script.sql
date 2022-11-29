
CREATE TABLE `user` (
    `userName` VARCHAR(100) PRIMARY KEY ,
    `password` VARCHAR(100) NOT NULL ,
    `fullName` VARCHAR (200) NOT NULL
);

CREATE TABLE `to-do-item`(
    `id` INT AUTO_INCREMENT PRIMARY KEY ,
    `username` VARCHAR(100) NOT NULL ,
    `description` VARCHAR(500) NOT NULL ,
    `status` ENUM('DONE','NOT_DONE'),
    CONSTRAINT fk_name FOREIGN KEY (`username`) REFERENCES `user` (`userName`)
);

INSERT INTO `user` (`userName`, `password`, `fullName`) VALUES ('Manelka','123456789','Manelka Nishan'),
                                                       ('Visal','2345678','Visal Srimal'),
                                                       ('Pradeep','1234567','pradeep sampath'),
                                                       ('Naveen','432562345','Naveen samaranayaka'),
                                                       ('Chathura','836292702','Chathura Rupe');

INSERT INTO `to-do-item` (`username`, `description`, `status`) VALUES ('Manelka','Go home gota','DONE'),
                                                                ('Visal','Go Home Ranil','NOT_DONE');


