package org.example;

public enum Base {
    VANILLE(1.50),
    CHOCOLAT(1.75),
    NATURE(1.25),
    PISTACHE(2.00);

    private final double prix;

    Base(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }
}