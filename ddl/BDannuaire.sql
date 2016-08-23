
    alter table Adresse 
        drop 
        foreign key FK1F8FC88FE5309C10;

    alter table Personne_Numero 
        drop 
        foreign key FK415C43EFAD3B65F5;

    alter table Personne_Numero 
        drop 
        foreign key FK415C43EF4310FD5;

    drop table if exists Adresse;

    drop table if exists Numero;

    drop table if exists Personne;

    drop table if exists Personne_Numero;

    create table Adresse (
        id integer not null auto_increment,
        rue varchar(255),
        ville varchar(255),
        personne_id integer,
        primary key (id)
    );

    create table Numero (
        id integer not null auto_increment,
        tel varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table Personne (
        id integer not null auto_increment,
        nom varchar(255),
        prenom varchar(255),
        dateNaissance date,
        primary key (id)
    );

    create table Personne_Numero (
        personnes_id integer not null,
        numeros_id integer not null,
        primary key (personnes_id, numeros_id)
    );

    alter table Adresse 
        add index FK1F8FC88FE5309C10 (personne_id), 
        add constraint FK1F8FC88FE5309C10 
        foreign key (personne_id) 
        references Personne (id);

    alter table Personne_Numero 
        add index FK415C43EFAD3B65F5 (numeros_id), 
        add constraint FK415C43EFAD3B65F5 
        foreign key (numeros_id) 
        references Numero (id);

    alter table Personne_Numero 
        add index FK415C43EF4310FD5 (personnes_id), 
        add constraint FK415C43EF4310FD5 
        foreign key (personnes_id) 
        references Personne (id);
