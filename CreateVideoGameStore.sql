DROP DATABASE IF EXISTS VideogameShop;
Create database VideogameShop;

use VideogameShop;

CREATE TABLE IF NOT EXISTS Genre(
GenreId Int not null auto_increment,
GenreName varchar(200) not null,
Primary Key (GenreId)
);

CREATE TABLE IF NOT EXISTS Game(
GameId Int not null auto_increment,
GameName varchar(200) not null,
GamePrice int not null,
ReleaseDate varchar(200) not null,
Genre int not null,
Primary Key (GameId),
foreign key (Genre) references Genre(GenreId)
);

CREATE TABLE IF NOT EXISTS GameSales(
SaleId Int not null auto_increment,
GameTitle int not null,
Price double not null,
Primary Key (SaleId),
foreign key (GameName) references Game(id) /*how does this work?*/
);

CREATE TABLE IF NOT EXISTS Customer(
CustomerId Int not null auto_increment,
CustomerName varchar(200),
CustomerEmail varchar (200),
Primary Key (Id)
);

INSERT INTO Genre(GenreId, GenreName) VALUES (1, "Action");
INSERT INTO Genre(GenreId, GenreName) VALUES (2, "RPG");
INSERT INTO Genre(GenreId, GenreName) VALUES (3, "Shooter");
INSERT INTO Genre(GenreId, GenreName) VALUES (4, "Platformer");
INSERT INTO Genre(GenreId, GenreName) VALUES (5, "Horror");
INSERT INTO Genre(GenreId, GenreName) VALUES (6, "Fighting");

INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (1,"God Of War", 60, "April 20, 2018","Action");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (2,"The Last Of Us", 70, "June 14, 2013","Action");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (3,"Marvel's Spider-Man 2", 70, "October 20, 2023","Action");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (4,"Diablo", 70, "June 5, 2023", "RPG");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (5,"Elden Ring", 60, "February 25, 2022","RPG");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (6,"Baldur's Gate 3", 70, "August 3, 2023","RPG");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (7,"Tom Clancy's Rainbow Six Siege", 70, "December 1, 2015","Shooter");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (8,"Call Of Duty: Modern Warfare 3", 70, "November 10, 2023","Shooter");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (9,"Titanfall 2", "October 28, 2016", 60, "Shooter");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (10,"New Super Mario Bros.", 25, "May 15, 2006","Platformer");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (11,"Alto's Odyssey", 8, "February 21, 2018","Platformer");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (12,"Astro Bot Rescue Mission", 20, "October 2, 2018","Platformer");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (13,"OutLast", 40, "September 4, 2013","Horror");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (14,"Phasmaphobia", 14, "September 4, 2020","Horror");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (15,"Dead By Daylight", 20, "June 14, 2016","Horror");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (16,"Mortal Kombat 11", 50, "April 23, 2019","Fighting");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (17,"Super Smash Bros. Ultimate", 60, "December 7, 2018","Fighting");
INSERT INTO Game(GameId, GameName, GamePrice, ReleaseDate, Genre) VALUES (18,"Soulcalibur VI", 30, "October 19, 2018","Fighting");
