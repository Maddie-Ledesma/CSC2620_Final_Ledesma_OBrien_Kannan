# Project Overview

## Project summary

This is an application that helps run a Video game store, having many features to help manage and run it including:

- A selection menu that’s easy to read and navigate
- A feature to show games in stock
- A feature to purchase a game and add the sale to the DB
- Track Customer Purchases
- Showcases Popular Games
- A Search by Genre Feature

## Functional Dependencies

- Game relies on GenreId to define what kind of game it is
- SalesDetails Relies on SalesId and GameId to determine what game was sold and which sale it was
- Sales relies on CustomerId to determine what customer bought what game at what time
