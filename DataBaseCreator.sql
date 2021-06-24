DROP database MakeYourOwnPc;
create database MakeYourOwnPc;
USE MakeYourOwnPc;

create table Users
(
    email     CHAR(40) PRIMARY KEY,
    firstname char(30) not null,
    lastname  char(30) not null,
    admin     boolean default false,
    password  char(64) not null
);

create table Memories
(
    id             int primary key auto_increment,
    name           char(100) not null unique,
    mtype          boolean   not null,#false= ram true=massStorage
    socket         char(10)  not null,
    consumption    int       not null,
    price          float     not null,
    amountmemories tinyint   not null,
    stock          int       not null,
    imagepath      char(100)
);

create table Motherboards
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
    stock          int       not null,
    imagepath      char(100)
);

create table Gpus
(
    id          int primary key auto_increment,
    name        char(100) not null unique,
    consumption int       not null,
    price       float     not null,
    stock       int       not null,
    imagepath   char(100)
);


create table Cpus
(
    id            int primary key auto_increment,
    name          char(100) not null unique,
    socket        char(10)  not null,
    integratedgpu boolean   not null,
    consumption   int       not null,
    price         float     not null,
    stock         int       not null,
    imagepath     char(100)
);

create table Psus
(
    id        int primary key auto_increment,
    name      char(100) not null unique,
    power     int       not null,
    price     float     not null,
    stock     int       not null,
    imagepath char(100)
);

create table Pccases
(
    id         int primary key auto_increment,
    name       char(100) not null unique,
    formfactor char(10)  not null,
    price      float     not null,
    stock      int       not null,
    imagepath  char(100)
);


create table Builds
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

create table Memoriesbuiltin
(
    idbuild          int not null,
    amountofmemories int not null,
    id               int not null,
    primary key (idbuild, id),
    foreign key (idbuild) references builds (id),
    foreign key (id) references memories (id)
);

create table Countries
(
    id    char(2) primary key,
    label char(20) unique
);

create table Purchases
(
    id              int primary key auto_increment,
    idbuild         int      not null,
    creationdate    datetime not null,
    cellphone char(13) not null,
    price      float    not null,
    address         char(40) not null,
    city            char(30) not null,
    cap             char(6)  not null,
    email           char(40) not null,
    countryid       char(2)  not null,
    foreign key (idbuild) references builds (id),
    foreign key (countryid) references Countries (id)
)







