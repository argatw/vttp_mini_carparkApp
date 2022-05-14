-- drop database if exist cpDatabase;

create database cpDatabase;

use cpDatabase;

create table user (
    userId varchar(64) PRIMARY KEY not null,
    password varchar(64) not null,
    email varchar(64) not null
    -- primary key(userId);
);

create table carparkSearched ( 
    searchId int auto_increment,
    userId varchar(64) not null, 
    development varchar(128) not null,
    availableLots int(5) not null,

    primary key(searchId),
    constraint fk_userId
    foreign key(userId) references user(userId)
);