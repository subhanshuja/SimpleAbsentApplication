
drop database if exists `absent`;

create database `absent`;
use `absent`;

drop table if exists `absent`;

create table `absentin` (
   `id`   int not null primary key auto_increment,
   `name` varchar(50) not null,
   `nim`  varchar(13) not null unique,
   `absent` int(13) not null,
   index(name, nim)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_general_ci;

create table `absentout` (
   `id`   int not null primary key auto_increment,
   `name` varchar(50) not null,
   `nim`  varchar(13) not null unique,
   `absentout` int(13) not null,
   index(name, nim)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_general_ci;