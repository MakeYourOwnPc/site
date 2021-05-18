DROP database MakeYourOwnPc;
create database MakeYourOwnPc;
USE MakeYourOwnPc;

create table users
(
    email     CHAR(40) PRIMARY KEY,
    firstname char(30) not null,
    lastname  char(30) not null,
    admin     boolean default false,
    password  char(64) not null
);

create table memories
(
    id             int primary key auto_increment,
    name           char(100) not null unique,
    mtype          boolean   not null,#false= ram true=massStorage
    socket         char(10)  not null,
    consumption    int       not null,
    price          float     not null,
    amountmemories tinyint   not null,
    stock          int       not null
);

create table motherboards
(
    id             int primary key auto_increment,
    name           char(100) not null unique,
    amountslotnvme tinyint   not null,
    amountslotsata tinyint   not null,
    amountslotram  tinyint   not null,
    cpusocket      char(10)  not null,
    ramsocket      char(10)  not null,
    formfactor     char(10)  not null,
    consumption    int       not null,
    price          float     not null,
    stock          int       not null
);

create table gpus
(
    id          int primary key auto_increment,
    name        char(100) not null unique,
    consumption int       not null,
    price       float     not null,
    stock       int       not null
);


create table cpus
(
    id            int primary key auto_increment,
    name          char(100) not null unique,
    socket        char(10)  not null,
    integratedgpu boolean   not null,
    consumption   int       not null,
    price         float     not null,
    stock         int       not null
);

create table psus
(
    id    int primary key auto_increment,
    name  char(100) not null unique,
    power int       not null,
    price float     not null,
    stock int       not null
);

create table pccases
(
    id         int primary key auto_increment,
    name       char(100) not null unique,
    formfactor char(10)  not null,
    price      float     not null,
    stock      int       not null
);


create table builds
(
    id        int primary key auto_increment,
    mobo      int      not null,
    gpu       int,
    psu       int      not null,
    pccase    int      not null,
    maker     char(40) not null,
    suggested boolean  not null,
    type      char(20) not null,
    foreign key (mobo) references motherboards (id) on delete cascade on update no action,
    foreign key (gpu) references gpus (id) on delete cascade on update no action,
    foreign key (psu) references psus (id) on delete cascade on update no action,
    foreign key (pccase) references pccases (id) on delete cascade on update no action,
    foreign key (maker) references users (email)

);

create table memoriesbuiltin
(
    idbuild          int not null,
    amountofmemories int not null,
    id               int,
    primary key (idbuild, id),
    foreign key (idbuild) references builds (id),
    foreign key (id) references memories (id)
);

create table country
(
    id    char(2) primary key,
    label char(20) unique
);

create table purchases
(
    id              int primary key auto_increment,
    idbuild         int      not null,
    foreign key (idbuild) references builds (id),
    creationdate    datetime not null,
    cellphonenumber char(13) not null,
    totalprice      float    not null,
    address         char(40) not null,
    city            char(30) not null,
    cap             char(6)  not null,
    email           char(40) not null,
    countryid       char(2)  not null,
    foreign key (countryid) references country (id)
)








