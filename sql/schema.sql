-- drop database if exist cpDatabase;

create database cpDatabase;

use cpDatabase;

create table user (
    userId varchar(64) not null,
    password varchar(64) not null,
    email varchar(64) PRIMARY KEY not null 
    -- primary key(userId);
);

create table favourite ( 
    searchId int auto_increment,
    email varchar(64) not null, 
    trainLine varchar(64) not null,
    station varchar(64) not null,

    primary key(searchId),
    constraint fk_email
    foreign key(email) references user(email)
);