-- create database and user
create database chat;
create user admin with encrypted password 'admin';
grant all privileges on database chat to admin;

-- connect to 'chat' database as 'admin' user
\c chat admin

create table if not exists users (
    id serial primary key,
    nick text not null unique,
    login text not null unique,
    password text not null unique
);

insert into users (nick, login, password) values
('User_1', 'login1', 'pass1'),
('User_2', 'login2', 'pass2'),
('User_3', 'login3', 'pass3')