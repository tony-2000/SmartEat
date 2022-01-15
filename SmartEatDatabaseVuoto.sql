drop Database if exists SmartEat;
Create Database SmartEat;
use SmartEat;

Create Table Utente(
codiceFiscale char(16) primary key not null,
nome varchar(20) not null,
cognome varchar(20) not null,
sesso char(1) not null,
dataDiNascita date not null,
luogoDiNascita varchar(25) not null,
email varchar(35) unique not null,
residenza varchar(35) not null,
password varchar(200) not null,
amministratore boolean not null,
accepted boolean not null
);

Create Table Tessera(
codiceFiscale char(16) primary key not null,
foreign key (codiceFiscale) references Utente(codiceFiscale)
on update cascade
on delete cascade,
saldo float not null

);

Create Table Pietanza(
nome varchar(25) not null primary key,
descrizione varchar(300) not null,
tipo char(1) not null,
ingredienti varchar(300) not null,
immagine varchar(100) not null,
numeroAcquisti int not null
);

Create Table Menu(
codiceMenu int not null primary key auto_increment,
nome varchar(25) not null,
primo varchar(20) not null,
foreign key (primo) references Pietanza(nome)
on update cascade
on delete cascade,
secondo varchar(20) not null,
foreign key (secondo) references Pietanza(nome)
on update cascade
on delete cascade,
dessert varchar(20) not null,
foreign key (dessert) references Pietanza(nome)
on update cascade
on delete cascade,
descrizione varchar(500) not null,
immagine varchar(100) not null,
prezzo float not null,
available boolean not null
);

Create Table Acquisto(
dataAcquisto date not null,
codiceFiscale char(16) not null,
foreign key (codiceFiscale) references Utente(codiceFiscale)
on update cascade
on delete cascade,
codiceMenu int not null,
foreign key (codiceMenu) references Menu(codiceMenu)
on update cascade
on delete cascade,
postoMensa boolean not null,
primary key(dataAcquisto, codiceFiscale, codiceMenu)
);

Create Table Mensa(
nome varchar(25) not null primary key,
postiDisponibili int,
orarioApertura time not null,
orarioChiusura time not null
);




#Popolazione

insert into Utente values
("AMMINI00S00T0000", "Admin", "Admin", "N", "1970-01-01", "SmartEat", "admin@admin.admin", "SmartEat", "e9ef97768a87df85ade3bba23567b5491b6b2884", true, true);

insert into Tessera values
("AMMINI00S00T0000", "500.00");

insert into Mensa values
("SmartEat", "500", "12:00", "15:00");