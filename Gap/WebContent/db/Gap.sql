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
Telefono varchar(50),
primary key (Email)
);

create table Ruolo(
	Nome varchar(15) not null,
    primary key(nome)
);

create table Utente_Ruolo(
	Email varchar(25) not null,
    Ruolo varchar(15) not null,
    primary key(Email, Ruolo),
    foreign key (Email) references Utente(Email) on update cascade on delete cascade,
    foreign key (Ruolo) references Ruolo(Nome) on update cascade on delete cascade
);

create table carta (
Numero char(16) not null,
Anno_scadenza date not null,
Proprietario varchar(50) not null,
Email char(25) not null,
primary key (Numero),
foreign key (Email) references Utente(Email) on update cascade on delete cascade
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
Email char(25) not null,
primary key (Email,Codice),
foreign key (Email) references utente(Email) on update cascade on delete cascade,
foreign key (Codice) references Prodotto (Codice) on update cascade on delete cascade
);

create table Ordine(
Num_Ordine int auto_increment,
Email char(25) not null,
Prezzo_Totale int not null,
Num_Prodotti int not null,
data_Ordine date not null,
primary key(Num_ordine),
foreign key(Email) references Utente(Email) 
)auto_increment=1;

create table Composizione
(
Num_Ordine int not null,
Codice int not null,
Quantita int not null,
primary key(Num_Ordine, Codice),
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


Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ("zeus",150,50,75,"manuale",15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('arrow',175,120,100,'elettrica',200,250,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('markus',162,90,150,'letto',20,200,3);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('hugò',158,50,75,'manuale',15,150,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('robert',145,50,75,'letto',15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('silandrio',120,50,80,'manuale',25,100,23);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('pouf',45,60,100,'pouf',150,20,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('zelbio',25,70,75,'pouf',15,150,15);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('attr1',240,58,205,'moderna',25,250,5);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('attr2',236,65,200,'classica',150,300,0);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('attr3',200,45,200,'classica',350,200,25);
Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('attr4',150,50,75,'moderna',15,150,15);


Insert into Materiale (Id,Tipologia_Materiale,Colore) values (1,'pelle','rosso'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (2,'abelia','monocromo'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (3,'begonia','marrone');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (4,'lilum','blu');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (5,'liroe','bianco'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (6,'pelle','nero'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (7,'pelle','ombra'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (8,'santolina','bordeaux');
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (9,'pelle','beige'); 
Insert into Materiale (Id,Tipologia_Materiale,Colore) values (10,'solidago','monocromo');


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

Insert into Utente (CF,Nome,Cognome,Email,Passw) values ('dnlgnt98a05g813p','Giacinto','Adinolfi','giaci95@live.it','root');
Insert into Utente (CF,Nome,Cognome,Email,Passw) values ('dnlotp254689541d','Vincenzo','Palcone','vincenzo85@gmail.com','root');
Insert into Utente (CF,Nome,Cognome,Email,Passw,Indirizzo_Fatturazione,Telefono) values ('dnn5sd47efr85gfd','Emanuele','Giammarino','giammarino85@gmail.com','emanuele','Via anice','3485625478' );

Insert into Ruolo (Nome) values ('amministratore');
Insert into Ruolo (Nome) values ('utente');

Insert into Utente_Ruolo (Email, Ruolo) values ('giaci95@live.it','amministratore');
Insert into Utente_Ruolo (Email, Ruolo) values ('vincenzo85@gmail.com','amministratore');
Insert into Utente_Ruolo (Email, Ruolo) values ('giammarino85@gmail.com','utente');
