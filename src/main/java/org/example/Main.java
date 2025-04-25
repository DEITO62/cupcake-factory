package org.example;

import java.util.*;

public class Main {

    //Exercice 3
    public static void main(String[] args) {
        CupcakeFactory factory = new CupcakeFactory();

        Cupcake c1 = new Cupcake(Base.CHOCOLAT, Cream.NUTELLA, Topping.BUENO);
        Cupcake c2 = new Cupcake(Base.PISTACHE, Cream.ELMORJEN, Topping.OREO, Topping.BANANE);
        Cupcake c3 = new Cupcake(Base.VANILLE, Cream.VANILLE);
        c3.setCupDuJ(); // cupcake du jour avec réduction

        factory.vendreCupcake(c1);
        factory.vendreCupcake(c2);
        factory.vendreCupcake(c3);

        factory.afficherVentes();

        double chiffreAffaires = factory.chiffreAffairesTotal();
        System.out.printf("Chiffre d'affaires total : %.2f€%n", chiffreAffaires);
    }
}
