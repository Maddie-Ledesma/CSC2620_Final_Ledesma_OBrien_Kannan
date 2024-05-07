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

INSERT INTO Sales(Id, SalesDate, CustomerId) VALUE (1, '2020-12-23', 1); 
INSERT INTO Sales(Id, SalesDate, CustomerId) VALUE (2, "2017-12-23", 2);
INSERT INTO Sales(Id, SalesDate, CustomerId) VALUE (3, "2019-5-20", 3);
INSERT INTO Sales(Id, SalesDate, CustomerId) VALUE (4, "2020-1-25", 4);

INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(1, 1, 1, 60);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(1, 2, 1, 70);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(1, 3, 1, 70);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(1, 18, 1, 30);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(2, 1, 1, 60);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(2, 6, 1, 70);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(2, 12, 1, 20);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(2, 16, 1, 50);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(3, 1, 1, 60);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(3, 6, 1, 70);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(3, 10, 1, 25);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(3, 17, 1, 60);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(4, 1, 1, 60);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(4, 6, 1, 70);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(4, 10, 1, 25);
INSERT INTO SalesDetails(SalesId, GameId, Quantity, Price) VALUES(4, 17, 1, 60);

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
CREATE PROCEDURE searchByGenre (GenreSearchId int )
BEGIN 

SELECT g.Id, g.Name, g.ReleaseDate, g.Quantity, g.GenreId, g.Price, ge.Name as GenreName
FROM Game as g join Genre as ge on g.GenreId = ge.id
WHERE ge.Id = GenreSearchId
ORDER BY g.Name;

END; $$
delimiter ;

delimiter $$
DROP PROCEDURE IF EXISTS showGamesInStock$$
CREATE PROCEDURE showGamesInStock ()
BEGIN
    SELECT ga.Id, ga.Name, ga.ReleaseDate, ga.Quantity, ga.GenreId, ga.Price, ge.Name as GenreName
    FROM Game AS ga
    INNER JOIN Genre AS ge ON ge.Id = ga.GenreId
    ORDER BY ge.Name, ga.Name;
END; $$
delimiter ;


delimiter $$
Drop Procedure if exists PopularGames$$
create procedure PopularGames ()
BEGIN

SELECT Sum(sd.Quantity), g.Name
FROM Game as g join SalesDetails as sd on g.id = sd.GameId
Group by g.Name
ORDER BY 1 desc, 2 asc;

END; $$
delimiter ;

delimiter $$
Drop Procedure if exists TrackCustomerPurchases$$
create procedure TrackCustomerPurchases (CustomerSearchId int)
BEGIN

SELECT g.Name, sd.Quantity, sd.Price, s.SalesDate, ge.Name as GenreName
FROM Sales as s join SalesDetails as sd on s.id = sd.SalesId join Game as g on g.Id = sd.GameId join Genre as ge on g.GenreId = ge.Id
WHERE s.CustomerId = CustomerSearchId
ORDER BY 4, 1;

END; $$
delimiter ;

delimiter $$
DROP PROCEDURE IF EXISTS printCustomerReciept$$
CREATE PROCEDURE printCustomerReciept (gamePurchased varchar(200))
BEGIN

SELECT sd.SalesId, g.Name, sd.Price
FROM SalesDetails as sd JOIN Game as g on g.Id = sd.GameId
WHERE g.Name = gamePurchased

END; $$
delimiter ;
