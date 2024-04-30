DROP DATABASE IF EXISTS VideogameShop;
Create database VideogameShop;

use VideogameShop;

CREATE TABLE IF NOT EXISTS Genre(
Id Int not null auto_increment,
Name varchar(200) not null,
Primary Key (id)
);

CREATE TABLE IF NOT EXISTS Game(
Id Int not null auto_increment,
Name varchar(200) not null,
ReleaseDate varchar(200) not null,
Genre int not null,
Primary Key (Id),
foreign key (Genre) references Genre(id)
);

CREATE TABLE IF NOT EXISTS GameSales(
Id Int not null auto_increment,
GameName int not null,
Price double not null,
Primary Key (Id),
foreign key (GameName) references Game(id)
);

CREATE TABLE IF NOT EXISTS Customer(
id Int not null auto_increment,
Name varchar(200),
CustomerEmail varchar (200),
Primary Key (Id)
);

INSERT INTO Genre(Id, Name) VALUES (1, "Action");
INSERT INTO Genre(Id, Name) VALUES (2, "RPG");
INSERT INTO Genre(Id, Name) VALUES (3, "Shooter");
INSERT INTO Genre(Id, Name) VALUES (4, "Platformer");
INSERT INTO Genre(Id, Name) VALUES (5, "Horror");
INSERT INTO Genre(Id, Name) VALUES (6, "Fighting");

INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"God Of War","April 20, 2018","Action");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (2,"The Last Of Us","June 14, 2013","Action");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (3,"Marvel's Spider-Man 2","October 20, 2023","Action");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (4,"Diablo","June 5, 2023","RPG");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (5,"Elden Ring","February 25, 2022","RPG");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (6,"Baldur's Gate 3","August 3, 2023","RPG");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (7,"Tom Clancy's Rainbow Six Siege","December 1, 2015","Shooter");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (8,"Call Of Duty: Modern Warfare 3","November 10, 2023","Shooter");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (9,"Titanfall 2","October 28, 2016","Shooter");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (10,"Super Mario Bros.","May 15, 2006","Platformer");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (11,"Alto's Odyssey","February 21, 2018","Platformer");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (12,"Astro Bot Rescue Mission","October 2, 2018","Platformer");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"OutLast","September 4, 2013","Horror");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"Phasmaphobia","September 4, 2020","Horror");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"Dead By Daylight","June 14, 2016","Horror");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"Mortal Kombat 11","April 23, 2019","Fighting");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"Super Smash Bros. Ultimate","December 7, 2018","Fighting");
INSERT INTO Game(Id, Name, ReleaseDate, Genre) VALUES (1,"Soulcalibur VI","October 19, 2018","Fighting");