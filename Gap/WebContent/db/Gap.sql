drop database if exists gap;
create database gap;

Drop user if exists 'gap'@'localhost';
Create user 'gap'@'localhost' identified by 'gap';
grant all on gap.* to 'gap'@'localhost';

use gap;

create table Utente(
CF char(16) not null,
Nome varchar(15) not null,
Cognome varchar(15) not null,
Email varchar(25) not null,
Passw varchar(50) not null, 
Indirizzo_Fatturazione varchar(40),
Telefono int,
Tipologia int,
primary key (CF)
);

create table carta (
Numero char(16) not null,
Anno_scadenza date not null,
Proprietario varchar(50) not null,
CF char(16) not null,
primary key (Numero),
foreign key (CF) references utente(CF) on update cascade on delete cascade
);

create table Prodotto(
Codice int not null auto_increment,
Nome varchar(15) not null,
Altezza int not null,
Profondita int not null,
Larghezza int not null,
Tipologia varchar(25) not null,
Quantita int not null,
Prezzo float not null,
Sconto int not null,
primary key(Codice)
)auto_increment=1;

create table immagine(
Id int not null auto_increment,
Codice int not null,
foto mediumblob not null,
primary key (Id),
foreign key (Codice) references Prodotto (Codice) on update cascade on delete cascade
)auto_increment=1;

create table recensione (
Testo varchar(250) not null,
Data_Recensione date not null,
Codice int not null,
CF char(16) not null,
primary key (CF,Codice),
foreign key (CF) references utente(CF) on update cascade on delete cascade,
foreign key (Codice) references Prodotto (Codice) on update cascade on delete cascade
);

create table Ordine(
Num_Ordine int auto_increment,
CF char(16) not null,
Prezzo_Totale int not null,
Num_Prodotti int not null,
data_Ordine date not null,
primary key(Num_ordine),
foreign key(CF) references Utente(CF) 
)auto_increment=1;

create table Composizione
(
Id int not null,
Num_Ordine int not null,
Codice int not null,
Num_elementi int not null,
primary key(Id),
foreign key(Codice) references Prodotto(Codice),
foreign key(Num_Ordine) references Ordine(Num_ordine)
);

Create table Materiale
(
Id int not null,
Tipologia_Materiale varchar(20) not null,
Colore varchar(20) not null,
primary key(Id)
);

create table Composto(
Id int not null,
Codice int not null,
primary key(Id,Codice),
foreign key(Codice) references Prodotto(Codice) on update cascade on delete cascade,
foreign key(Id) references Materiale(Id) on update cascade on delete cascade
);


Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ("Zeus",150,50,75,"Manuale",15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Arrow',175,120,100,'Elettrica',200,250,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Markus',162,90,150,'Letto',20,200,3);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Hugò',158,50,75,'Manuale',15,150,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Robert',145,50,75,'Letto',15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Silandrio',120,50,80,'Manuale',25,100,23);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Pouf',45,60,100,'Pouf',150,20,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Zelbio',25,70,75,'Pouf',15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Attr1',240,58,205,'Moderna',25,250,5);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Attr2',236,65,200,'Classica',150,300,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Attr3',200,45,200,'Classica',350,200,25);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('Attr4',150,50,75,'Moderna',15,150,15);


Insert into Materiale (Id,Tipologia_Materiale,Colore) values (1,'pelle','Rosso'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (2,'Abelia','Monocromo'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (3,'Begonia','Marrone');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (4,'Lilum','Blu');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (5,'Liroe','Bianco'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (6,'pelle','Nero'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (7,'pelle','Ombra'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (8,'Santolina','Bordeaux');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (9,'Pelle','Beige'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (10,'Solidago','Monocromo');


Insert into Composto (Id,Codice) values (1,7);
Insert into Composto (Id,Codice) values (1,8);
Insert into Composto (Id,Codice) values (1,5);
Insert into Composto (Id,Codice) values (2,1);
Insert into Composto (Id,Codice) values (3,4);
Insert into Composto (Id,Codice) values (3,5);
Insert into Composto (Id,Codice) values (3,7);
Insert into Composto (Id,Codice) values (4,6);
Insert into Composto (Id,Codice) values (4,7);
Insert into Composto (Id,Codice) values (4,1);
Insert into Composto (Id,Codice) values (4,2);
Insert into Composto (Id,Codice) values (4,10);
Insert into Composto (Id,Codice) values (5,11);
Insert into Composto (Id,Codice) values (5,12);
Insert into Composto (Id,Codice) values (6,9);
Insert into Composto (Id,Codice) values (7,2);
Insert into Composto (Id,Codice) values (8,3);
Insert into Composto (Id,Codice) values (9,4);

Insert into Utente (CF,Nome,Cognome,Email,Passw,Tipologia) values ('dnlgnt98a05g813p','Giacinto','Adinolfi','Giaci95@live.it','root',1);
Insert into Utente (CF,Nome,Cognome,Email,Passw,Tipologia) values ('dnlotp254689541d','Vincenzo','Palcone','Vincenzo85@gmail.com','root',1);

insert into immagine (Codice,foto) value(1,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/zeus.jpg'));
insert into immagine (Codice,foto) value(2,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/arrow.jpg'));
insert into immagine (Codice,foto) value(3,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/markus.jpg'));
insert into immagine (Codice,foto) value(4,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/hugò.jpg'));
insert into immagine (Codice,foto) value(5,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/robert.jpg'));
insert into immagine (Codice,foto) value(6,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/silandrio.jpg'));
insert into immagine (Codice,foto) value(7,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/pouf.jpg'));
insert into immagine (Codice,foto) value(8,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/zelbio.jpg'));
insert into immagine (Codice,foto) value(9,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/attr1.jpg'));
insert into immagine (Codice,foto) value(10,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/attr2.jpg'));
insert into immagine (Codice,foto) value(11,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/attr3.jpg'));
insert into immagine (Codice,foto) value(12,LOAD_FILE('C:/Users/giaci/OneDrive/Desktop/TSW/ProgettoTSW/Poltorne/attr4.jpg'));


