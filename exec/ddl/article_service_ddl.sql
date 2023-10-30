create database article;

use article;

drop table if exists article;
create table article
(
    article_id         bigint       not null primary key auto_increment,
    title              varchar(100) null,
    sub_title          varchar(100) null,
    writer             varchar(50)  null,
    published_date     timestamp    not null,
    content            longtext     null,
    thumbnail_img      varchar(300) null,
    created_date       timestamp    not null default now(),
    last_modified_date timestamp    not null default now(),
    all_keywords       text         null,
    top_keywords       text         null,
    html_content       longtext     null
) default character set utf8
  collate utf8_general_ci;

drop table if exists article_read;
create table article_read
(
    article_read_id    bigint       not null primary key auto_increment,
    article_id         bigint       null,
    member_key         varchar(100) null,
    created_date       timestamp    not null default now(),
    last_modified_date timestamp    not null default now(),
    foreign key (article_id) references article (article_id)
) default character set utf8
  collate utf8_general_ci;