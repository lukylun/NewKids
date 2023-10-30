create database user;

use user;

drop table if exists members;
create table members
(
    member_id          bigint       not null primary key auto_increment,
    member_key         varchar(100) not null unique,
    email              varchar(100) not null unique,
    encrypted_pwd      varchar(255) not null,
    name               varchar(20)  not null,
    age                int          not null,
    level              int          not null default 1,
    exp                int          not null default 0,
    nickname           varchar(20)  not null unique,
    active             tinyint      not null default true,
    created_date       timestamp    not null default now(),
    last_modified_date timestamp    not null default now()
) default character set utf8
  collate utf8_general_ci;

desc members;
