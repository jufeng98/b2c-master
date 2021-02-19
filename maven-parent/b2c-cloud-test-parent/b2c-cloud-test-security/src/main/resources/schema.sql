drop table users if exists;
CREATE TABLE users
(
    userid   char(12) primary key,
    username varchar(50),
    password varchar(100),
    enabled  int
);
drop table authorities if exists;
CREATE TABLE authorities
(
    authorityname varchar(10) primary key,
    userid        char(12)
);
drop table groups if exists;
CREATE TABLE groups
(
    groupid   char(6) primary key,
    groupname varchar(10)
);
drop table groups_users if exists;
CREATE TABLE groups_users
(
    groupid char(6),
    userid  varchar(12)
);
drop table groups_authorities if exists;
CREATE TABLE groups_authorities
(
    groupid       char(6),
    authorityname varchar(12)
);
drop table person if exists;
CREATE TABLE person
(
    id   int primary key,
    name varchar(50),
    age  int,
    sex  int
);
