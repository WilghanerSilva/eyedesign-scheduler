CREATE TABLE users (
    id varchar(100) primary key,
    email varchar(100) not null unique,
    password varchar(100) not null,
    firstname varchar(50),
    lastname varchar(50),
    phone varchar(50),
    role varchar(50) not null
);