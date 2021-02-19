drop table if exists authorities;
drop table if exists users;
drop table if exists group_authorities;
drop table if exists group_members;
drop table if exists groups;
drop table if exists persistent_logins;

create table users
(
    username    varchar(32) primary key,
    password    char(80) not null,
    name        varchar(32),
    mobile      varchar(20),
    phone       varchar(20),
    email       varchar(50),
    enabled     boolean  not null,
    first_login boolean  not null
);
create table authorities
(
    username  varchar(32) not null,
    authority varchar(32) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);
create table groups
(
    id         bigint auto_increment primary key,
    group_name varchar(32) not null
);
create table group_authorities
(
    group_id  bigint      not null,
    authority varchar(32) not null,
    constraint fk_group_authorities_group foreign key (group_id) references groups (id)
);
create table group_members
(
    id       bigint auto_increment primary key,
    username varchar(32) not null,
    group_id bigint      not null,
    constraint fk_group_members_group foreign key (group_id) references groups (id)
);
create table persistent_logins
(
    username  varchar(32) not null,
    series    varchar(64) primary key,
    token     varchar(64) not null,
    last_used timestamp   not null
);
-- 创建admin用户,密码123456
insert into users(username, password, name, mobile, phone, email, first_login, enabled)
values ('admin', '9196f613556c166e112048224ac1aa0f65c3e3d7f5b451879321bd436f90e26fe642301b5e44ec5f', 'admin', '',
        '13800138000', 'admin@qq.com', 0, 1);
-- 创建ROLE_ADMIN权限并授予给admin用户
insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN');
insert into authorities(username, authority)
values ('admin', 'ROLE_ACTUATOR');
-- 创建GROUP_ADMIN权限组
insert into groups(group_name)
values ('GROUP_ADMIN');
-- 将ROLE_ADMIN权限加入GROUP_ADMIN权限组
insert into group_authorities(group_id, authority)
values ((select id from groups where group_name = 'GROUP_ADMIN'), 'ROLE_ADMIN');
-- 将admin用户加入GROUP_ADMIN权限组
insert into group_members(username, group_id)
values ('admin', (select id from groups where group_name = 'GROUP_ADMIN'));