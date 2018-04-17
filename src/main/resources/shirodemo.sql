drop table if exists user;
 CREATE TABLE `user` (
  `id` int(11) primary key auto_increment,
  `name` varchar(20)  NOT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `password` varchar(20)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 insert into user values(1,'lin',12,'2013-12-01','123456');
 insert into user values(2,'apple',34,'1999-12-01','123456');
 insert into user values(3,'evankaka',23,'2017-12-01','123456');