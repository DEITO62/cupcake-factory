package org.example;

public enum Topping {
    BUENO(2.00),
    OREO(1.75),
    CHOCOPOPS(1.50),
    BANANE(1.00);

    private final double prix;

    Topping(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }
}   