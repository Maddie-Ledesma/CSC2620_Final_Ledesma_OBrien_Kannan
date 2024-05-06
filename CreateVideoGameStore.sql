DROP DATABASE IF EXISTS VideoGameShop;
Create database VideoGameShop;

use VideoGameShop;

CREATE TABLE IF NOT EXISTS Genre(
    Id Int not null auto_increment,
    Name varchar(200) not null,
    Primary Key (Id)
);

CREATE TABLE IF NOT EXISTS Game (
    Id Int not null auto_increment,
    Name varchar(200) not null,
    Price double not null,
    ReleaseDate Date not null,
    Quantity int not null,
    GenreId int not null,
    Primary Key (Id),
    foreign key (GenreId) references Genre(Id)
);

CREATE TABLE IF NOT EXISTS Customer(
    Id Int not null auto_increment,
    FirstName varchar(200) not null,
    LastName varchar(200) not null,
    Email varchar (200),
    Primary Key (Id)
);

CREATE TABLE IF NOT EXISTS Sales(
    Id Int not null auto_increment,
    SalesDate timestamp not null,
    CustomerId int not null,
    Primary Key (Id),
    foreign key (CustomerId) references Customer(id)
);

CREATE TABLE IF NOT EXISTS SalesDetails(
  SalesId int not null,
  GameId int not null,
  Quantity int not null,
  Price double not null,
  foreign key (SalesId) references Sales(Id),
  foreign key (GameId) references Game(Id)
);

INSERT INTO Genre(Id, Name) VALUES (1, "Action");
INSERT INTO Genre(Id, Name) VALUES (2, "RPG");
INSERT INTO Genre(Id, Name) VALUES (3, "Shooter");
INSERT INTO Genre(Id, Name) VALUES (4, "Platformer");
INSERT INTO Genre(Id, Name) VALUES (5, "Horror");
INSERT INTO Genre(Id, Name) VALUES (6, "Fighting");

INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (1,"God Of War", 60, STR_TO_DATE("April 20, 2018", "%M %d, %Y"),1, 122);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (2,"The Last Of Us", 70, STR_TO_DATE("June 14, 2013", "%M %d, %Y"),1, 233);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (3,"Marvel's Spider-Man 2", 70, STR_TO_DATE("October 20, 2023", "%M %d, %Y"),1, 88);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (4,"Diablo", 70, STR_TO_DATE("June 5, 2023", "%M %d, %Y"), 2, 155);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (5,"Elden Ring", 60, STR_TO_DATE("February 25, 2022", "%M %d, %Y"),2, 58);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (6,"Baldur's Gate 3", 70, STR_TO_DATE("August 3, 2023", "%M %d, %Y"),2, 40);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (7,"Tom Clancy's Rainbow Six Siege", 70, STR_TO_DATE("December 1, 2015", "%M %d, %Y"),3, 22);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (8,"Call Of Duty: Modern Warfare 3", 70, STR_TO_DATE("November 10, 2023", "%M %d, %Y"),3, 344);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (9,"Titan Fall 2", 60, STR_TO_DATE("October 28, 2016", "%M %d, %Y"), 3, 37);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (10,"New Super Mario Bros.", 25, STR_TO_DATE("May 15, 2006", "%M %d, %Y"),4, 278);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (11,"Alto's Odyssey", 8, STR_TO_DATE("February 21, 2018", "%M %d, %Y"),4, 165);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (12,"Astro Bot Rescue Mission", 20, STR_TO_DATE("October 2, 2018", "%M %d, %Y"),4,97);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (13,"OutLast", 40, STR_TO_DATE("September 4, 2013", "%M %d, %Y"),5, 145);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (14,"Phasmaphobia", 14, STR_TO_DATE("September 4, 2020", "%M %d, %Y"),5, 96);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (15,"Dead By Daylight", 20, STR_TO_DATE("June 14, 2016", "%M %d, %Y"),5, 6);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (16,"Mortal Kombat 11", 50, STR_TO_DATE("April 23, 2019", "%M %d, %Y"),6, 34);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (17,"Super Smash Bros. Ultimate", 60, STR_TO_DATE("December 7, 2018", "%M %d, %Y"),6,32);
INSERT INTO Game(Id, Name, Price, ReleaseDate, GenreId, Quantity) VALUES (18,"Soul Calibur VI", 30, STR_TO_DATE("October 19, 2018", "%M %d, %Y"),6, 12);

INSERT INTO Customer(Id, FirstName, LastName) VALUE (1, "John", "Doe");
INSERT INTO Customer(Id, FirstName, LastName) VALUE (2, "Maria", "Doe");
INSERT INTO Customer(Id, FirstName, LastName) VALUE (3, "Will", "Doe");
INSERT INTO Customer(Id, FirstName, LastName) VALUE (4, "Jose", "Doe");

delimiter $$
DROP PROCEDURE IF EXISTS addToGameSales$$ 
CREATE PROCEDURE addToGameSales (newSaleID int, newGameTitle varchar(200), newPrice int)
BEGIN
INSERT INTO GameSales(SaleID, GameTitle, Price)
VALUES (newSaleID, newGameTitle, newPrice);
END;
$$
delimiter ;

delimiter $$
DROP PROCEDURE IF EXISTS searchByGenre$$
CREATE PROCEDURE searchByGenre (newGenreSearch varchar(200))
BEGIN 
SELECT Game.GameName, Game.GamePrice, Game.Genre
FROM Genre
INNER JOIN Game ON Genre.GenreName = Game.Genre
WHERE Genre.GenreName = newGenreSearch
ORDER BY Game.GameName;
END; $$
delimiter ;