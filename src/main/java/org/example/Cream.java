package org.example;

public enum Cream {
    NUTELLA(2.00),
    VANILLE(1.50),
    ELMORJEN(2.25);

    private final double prix;

    Cream(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }
}