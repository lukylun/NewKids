create database recommendation;

use recommendation;

create table article_log
(
    article_log_id     bigint    not null primary key auto_increment,
    article_id         bigint    not null,
    count              int       not null,
    created_date       timestamp not null default now(),
    last_modified_date timestamp not null default now()
) default character set utf8
  collate utf8_general_ci;

create table keyword_log
(
    keyword_log_id     bigint    not null primary key auto_increment,
    keyword_id         bigint    not null,
    count              int       not null,
    created_date       timestamp not null default now(),
    last_modified_date timestamp not null default now()
) default character set utf8
  collate utf8_general_ci;