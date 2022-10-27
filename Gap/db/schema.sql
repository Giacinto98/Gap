create table if not exists Utente(
CF char(16) not null,
Nome varchar(15) not null,
Cognome varchar(15) not null,
Email varchar(50) not null,
Passw varchar(50) not null, 
Indirizzo_Fatturazione varchar(40),
Telefono varchar(50),
primary key (Email)
);

create table if not exists Ruolo(
	Nome varchar(15) not null,
    primary key(nome)
);

create table if not exists Utente_Ruolo(
	Email varchar(50) not null,
    Ruolo varchar(15) not null,
    primary key(Email, Ruolo),
    foreign key (Email) references Utente(Email), 
    foreign key (Ruolo) references Ruolo(Nome) 
);

create table if not exists Prodotto(
Codice int not null,
Nome varchar(25) not null,
Altezza int not null,
Profondita int not null,
Larghezza int not null,
Tipologia varchar(25) not null,
Quantita int not null,
Prezzo float not null,
Sconto int not null,
primary key(Codice)
);

create table if not exists recensione (
Testo varchar(250) not null,
Data_Recensione date not null,
Codice int not null,
Email varchar(50) not null,
primary key (Email,Codice)
);

create table if not exists Ordine(
Num_Ordine int not null,
Email varchar(50) not null,
Prezzo_Totale int not null,   
Num_Prodotti int not null,
data_Ordine varchar(15) not null,
NumeroCarta varchar (16) not null,
MeseScadenzaCarta varchar (2) not null,
AnnoScadenzaCarta varchar (4) not null,
Cvv varchar (3) not null,
primary key(Num_ordine)
);

create table if not exists Composizione
(
Num_Ordine int not null,
Codice int not null,
Id_Materiale varchar(20),
Quantita int not null,
primary key(Num_ordine, Codice, Id_Materiale)
);

Create table if not exists Materiale
(
Id int not null,
Tipologia_Materiale varchar(20) not null,
Colore varchar(20) not null,
primary key(Id)
);

create table if not exists Composto(
Id int not null,
Codice int not null,
primary key(Id,Codice),
foreign key(Codice) references Prodotto(Codice) 
);