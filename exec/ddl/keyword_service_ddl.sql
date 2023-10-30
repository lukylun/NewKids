create database keyword;

use keyword;

drop table if exists keyword;
create table keyword
(
    keyword_id         bigint      not null primary key auto_increment,
    word               varchar(20) not null unique,
    created_date       timestamp   not null default now(),
    last_modified_date timestamp   not null default now()
) default character set utf8
  collate utf8_general_ci;

drop table if exists article_keyword;
create table article_keyword
(
    article_keyword_id bigint    not null primary key auto_increment,
    keyword_id         bigint    null,
    article_key        bigint    null,
    created_date       timestamp not null default now(),
    last_modified_date timestamp not null default now(),
    foreign key (keyword_id) references keyword (keyword_id)
) default character set utf8
  collate utf8_general_ci;

drop table if exists keyword_search;
create table keyword_search
(
    keyword_search_id  bigint       not null primary key auto_increment,
    keyword_id         bigint       null,
    member_key         varchar(100) null,
    count              int          not null default 0,
    created_date       timestamp    not null default now(),
    last_modified_date timestamp    not null default now(),
    foreign key (keyword_id) references keyword (keyword_id)
) default character set utf8
  collate utf8_general_ci;