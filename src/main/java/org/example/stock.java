package org.example;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Base> bases = new ArrayList<>();
    private List<Cream> creams = new ArrayList<>();
    private List<Topping> toppings = new ArrayList<>();

    public void initialiserStock() {

        bases.add(new Base("Nature", 1.0, 5));
        bases.add(new Base("Chocolat", 1.2, 2));
        creams.add(new Cream("Vanille", 0.8, 3));
        creams.add(new Cream("Framboise", 0.9, 2));
        toppings.add(new Topping("Vermicelles", 0.4, 4));
        toppings.add(new Topping("Pépites", 0.5, 3));
    }



    public List<Base> getBasesDisponibles() {
        List<Base> disponibles = new ArrayList<>();
        for (Base b : bases) {
            if (b.isDisponible()) {
                disponibles.add(b);
            }
        }
        return disponibles;
    }

    public List<Cream> getCreamsDisponibles() {
        List<Cream> disponibles = new ArrayList<>();
        for (Cream c : creams) {
            if (c.isDisponible()) {
                disponibles.add(c);
            }
        }
        return disponibles;
    }

    public List<Topping> getToppingsDisponibles() {
        List<Topping> disponibles = new ArrayList<>();
        for (Topping t : toppings) {
            if (t.isDisponible()) {
                disponibles.add(t);
            }
        }
        return disponibles;
    }

    public Base getBaseByName(String name) {
        for (Base b : bases) {
            if (b.getNom().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public Cream getCreamByName(String name) {
        for (Cream c : creams) {
            if (c.getNom().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public Topping getToppingByName(String name) {
        for (Topping t : toppings) {
            if (t.getNom().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}
