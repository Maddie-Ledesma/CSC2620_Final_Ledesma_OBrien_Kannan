package model;

import java.sql.Date;

public class Game {
    private int id;
    private String name;
    private Double price;
    private Date releaseDate;
    private int quantity;

    private Genre genre;

    public Game(int id, String name, Double price, Date releaseDate, int quantity, Genre genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
        this.genre = genre;
    }
}
