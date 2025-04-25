package org.example;

import java.util.ArrayList;
import java.util.List;

public class CupcakeFactory {
    private List<Cupcake> ventes;

    public CupcakeFactory() {
        ventes = new ArrayList<>();
    }

    public void vendreCupcake(Cupcake cupcake) {
        ventes.add(cupcake);
    }

    public double chiffreAffairesTotal() {
        double total = 0;
        for (Cupcake c : ventes) {
            total += c.calculatePrice();
        }
        return total;
    }

    public void afficherVentes() {
        for (Cupcake c : ventes) {
            System.out.println(c);
        }
    }
}
