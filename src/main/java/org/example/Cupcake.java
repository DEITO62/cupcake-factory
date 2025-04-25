package org.example;

public class Cupcake {
    private Base base;
    private Cream cream;
    private Topping[] toppings;
    private boolean isCupDuJ;

    // Constructeur pour cup avc 1 ou 2 top
    public Cupcake(Base base, Cream cream, Topping... toppings) {
        if (toppings.length > 2) {
            throw new IllegalArgumentException("Un cupcake ne peut pas avoir plus de 2 toppings");
        }

        this.base = base;
        this.cream = cream;
        this.toppings = toppings;
        this.isCupDuJ = false;
    }

    // Calculer prix
    public double calculatePrice() {
        // Prix de base du cupcake (base + crème)
        double price = base.getPrix() + cream.getPrix();

        // Gestion prix toppings
        if (toppings.length == 1) {
            double prixTopping = toppings[0].getPrix();
        } else if (toppings.length == 2) {
            // Deux toppings, on garde le plus cher et on offre le moins cher
            double prixTopping1 = toppings[0].getPrix();
            double prixTopping2 = toppings[1].getPrix();

            price += Math.max(prixTopping1, prixTopping2);
        }

        // Réduction pour les cupcakes du jour
        if (isCupDuJ) {
            price *= 0.6; // 60% du prix normal
        }

        return price;
    }

    // Marquer comme cupcake du jour
    public void setCupDuJ() {
        this.isCupDuJ = true;
    }

    // Getters
    public Base getBase() {
        return base;
    }

    public Cream getCream() {
        return cream;
    }

    public Topping[] getToppings() {
        return toppings;
    }

    public boolean isCupDuJ() {
        return isCupDuJ;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cupcake [base=").append(base);
        sb.append(", cream=").append(cream);

        if (toppings.length > 0) {
            sb.append(", toppings=");
            for (int i = 1; i < toppings.length; i++) {
                sb.append(toppings[i]);
                if (i < toppings.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(", prix=").append(String.format("%.2f", calculatePrice())).append("€");
        if (isCupDuJ) {
            sb.append(" (Cupcake du jour)");
        }
        sb.append("]");

        return sb.toString();
    }
}