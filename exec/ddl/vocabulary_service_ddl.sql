create database vocabulary;

use vocabulary;

drop table if exists word;
create table word
(
    word_id            bigint      not null primary key auto_increment,
    word_key           varchar(10) not null,
    content            varchar(50) not null,
    description        longtext    not null,
    created_date       timestamp   not null default now(),
    last_modified_date timestamp   not null default now()
) default character set utf8
  collate utf8_general_ci;

desc word;

drop table if exists vocabulary;
create table vocabulary
(
    vocabulary_id      bigint       not null primary key auto_increment,
    word_id            bigint       null,
    member_key         varchar(100) null,
    `check`            tinyint      not null default false,
    created_date       timestamp    not null default now(),
    last_modified_date timestamp    not null default now(),
    foreign key (word_id) references word (word_id)
) default character set utf8
  collate utf8_general_ci;

desc vocabulary;