
drop database if exists `absent`;

create database `absent`;
use `absent`;

drop table if exists `absent`;

create table `absent` (
   `id`   int not null primary key auto_increment,
   `name` varchar(50) not null,
   `nim`  varchar(13) not null unique,
   `absentin` int(13) not null,
   `absentout` int(13) not null,
   index(name, nim)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_general_ci;