DROP database MakeYourOwnPc;
create database MakeYourOwnPc;
USE  MakeYourOwnPc;

create table users(
    email CHAR(40) PRIMARY KEY ,
    firstname char(30) not null,
    lastname char(30) not null,
    admin boolean default false,
    password char(64) not null
);

create table memories(
    sn char(11) PRIMARY KEY ,
    name char(100) not null unique,
    mtype boolean not null,#false= ram true=massStorage
    socket char(10) not null,
    consumption int not null,
    price float not null,
    amountmemories tinyint not null,
    available int not null
);

create table motherboards(
                       sn char(11) PRIMARY KEY ,
                       name char(100) not null unique,
                       amountslotnvme tinyint not null,
                       amountslotsata tinyint not null,
                       amountslotram tinyint not null,
                       cpusocket char(10) not null,
                       ramsocket char(10) not null,
                       formfactor char(10) not null,
                       consumption int not null,
                       price float not null,
                       available int not null
);

create table gpus(
                             sn char(11) PRIMARY KEY ,
                             name char(100) not null unique,
                             consumption int not null,
                             price float not null,
                             available int not null
);


create table cpus(
                             sn char(11) PRIMARY KEY ,
                             name char(100) not null unique,
                             cpusocket char(10) not null,
                            integratedgpu boolean not null,
                             consumption int not null,
                             price float not null,
                             available int not null
);

create table psus(
                             sn char(11) PRIMARY KEY ,
                             name char(100) not null unique,
                             power int not null,
                             price float not null,
                             available int not null
);

create table pccases(
                             sn char(11) PRIMARY KEY ,
                             name char(100) not null unique,
                             formfactor char(10) not null,
                             consumption int not null,
                             price float not null,
                             avaliable int not null
);


create table builds(
    id int primary key auto_increment,
    mobo char(11) not null,
    gpu char(11),
    psu char(11) not null,
    pccase char(11) not null,
    maker char(40) not null,
    suggested boolean not null,
    type char(20) not null,
    foreign key (mobo) references motherboards(sn) on delete cascade on update no action ,
    foreign key (gpu) references gpus(sn) on delete cascade on update no action ,
    foreign key (psu) references psus(sn) on delete cascade on update no action ,
    foreign key (pccase) references pccases(sn) on delete cascade on update no action ,
    foreign key (maker) references users(email)

);

create table memoriesbuiltin(
    idbuild int not null ,
    sn char(11) not null,
    primary key (idbuild,sn),
    foreign key (idbuild) references builds(id),
    foreign key (sn) references memories(sn)
);

create table country(
    id char(2) primary key ,
    label char(20) unique
);

create table purchases (
    id int primary key auto_increment,
    build int not null,
        foreign key (build) references builds(id),
    creationdate datetime not null ,
    cellphonenumber char(13) not null,
    totalprice float not null,
    address char(40) not null,
    city char(30) not null,
    cap char(6) not null,
    countryid char(2) not null,
        foreign key (countryid) references country(id)


)








