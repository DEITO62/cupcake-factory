import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.example.*;

public class Exercice_2 {

    private CupcakeFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new CupcakeFactory();
    }

    @Test
    public void testCreationCupcakeAvecBaseEtCreme() {
        assertDoesNotThrow(() -> new Cupcake(Base.VANILLE, Cream.NUTELLA));

        // test avec ingredients manquants -> erreur
        assertThrows(NullPointerException.class, () -> new Cupcake(null, Cream.VANILLE, Topping.BANANE));
        assertThrows(NullPointerException.class, () -> new Cupcake(Base.VANILLE, null, Topping.BANANE));
        assertThrows(NullPointerException.class, () -> new Cupcake(Base.VANILLE, Cream.NUTELLA, null));
    }

    @Test
    public void testLimiteDeuxToppings() {
        assertDoesNotThrow(() -> new Cupcake(Base.VANILLE, Cream.ELMORJEN));
        assertDoesNotThrow(() -> new Cupcake(Base.VANILLE, Cream.ELMORJEN, Topping.BUENO));
        assertDoesNotThrow(() -> new Cupcake(Base.VANILLE, Cream.ELMORJEN, Topping.BUENO, Topping.BANANE));

        // Test avec 3 toppings -> erreur
        assertThrows(IllegalArgumentException.class, () ->
                new Cupcake(Base.VANILLE, Cream.VANILLE, Topping.BUENO, Topping.OREO, Topping.CHOCOPOPS));
    }

    @Test
    public void testCalculPrixCupcakeSimple() {
        // Test: connaitre le prix
        Cupcake cupcake = new Cupcake(Base.VANILLE, Cream.VANILLE, Topping.BANANE);

        double prixAttendu = Base.VANILLE.getPrix() + Cream.VANILLE.getPrix() + Topping.BANANE.getPrix();
        assertEquals(prixAttendu, cupcake.calculatePrice(), 0.001);
    }

    @Test
    public void testReductionToppingMoinsCher() {
        // Test prix des topping, moins cher des 2 offert
        Topping toppingCher = Topping.OREO;
        Topping toppingMoinsCher = Topping.BANANE;

        Cupcake cupcake = new Cupcake(Base.VANILLE, Cream.VANILLE, toppingCher, toppingMoinsCher);

        // Le prix attendu est base + crème + topping le plus cher
        double prixAttendu = Base.VANILLE.getPrix() + Cream.VANILLE.getPrix() + toppingCher.getPrix();
        assertEquals(prixAttendu, cupcake.calculatePrice(), 0.001);

        // Vérifier avec l'ordre inverse des toppings
        Cupcake cupcake2 = new Cupcake(Base.VANILLE, Cream.VANILLE, toppingMoinsCher, toppingCher);
        assertEquals(prixAttendu, cupcake2.calculatePrice(), 0.001);
    }

    @Test
    public void testPrixCupcakeDuJour() {
        // Test: Quand je commande un cupcake du jour, son prix doit être de 60% son prix initial
        Cupcake cupcake = new Cupcake(Base.VANILLE, Cream.VANILLE, Topping.BANANE);
        double prixInitial = cupcake.calculatePrice();

        // Marquer comme cupcake du jour
        cupcake.setCupDuJ();

        // Vérifier la réduction de 60%
        assertEquals(prixInitial * 0.6, cupcake.calculatePrice(), 0.001);
    }

    @Test
    public void testPromotion5Plus1() {

        //6 cupcakes avec des prix différents
        List<Cupcake> cupcakes = new ArrayList<>();
        cupcakes.add(new Cupcake(Base.VANILLE, Cream.ELMORJEN, Topping.BANANE)); //4.75
        cupcakes.add(new Cupcake(Base.CHOCOLAT, Cream.VANILLE, Topping.BANANE)); //4.25 (moins cher)
        cupcakes.add(new Cupcake(Base.NATURE, Cream.NUTELLA, Topping.OREO)); //5.00
        cupcakes.add(new Cupcake(Base.PISTACHE, Cream.VANILLE, Topping.BUENO)); //5.50
        cupcakes.add(new Cupcake(Base.CHOCOLAT, Cream.NUTELLA, Topping.BUENO)); //5.75
        cupcakes.add(new Cupcake(Base.NATURE, Cream.VANILLE, Topping.OREO)); //4.50

        double coutAttendu = 0;
        for (Cupcake cupcake : cupcakes) {
            coutAttendu += cupcake.calculatePrice();
        }
        coutAttendu -= 4.25;

        // Vérifier le coût total avec la promotion
        assertEquals(coutAttendu, factory.calculerPrixCommande(cupcakes), 0.001);
    }

    @Test
    public void testPromotion5Plus1AvecCupcakeDuJour() {

        // Créer 6 cupcakes avec des prix différents, dont un est cupcake du jour
        List<Cupcake> cupcakes = new ArrayList<>();
        cupcakes.add(new Cupcake(Base.VANILLE, Cream.ELMORJEN, Topping.BANANE)); //4.75
        cupcakes.add(new Cupcake(Base.CHOCOLAT, Cream.VANILLE, Topping.BANANE)); //4.25
        cupcakes.add(new Cupcake(Base.NATURE, Cream.NUTELLA, Topping.OREO)); //5.00
        cupcakes.add(new Cupcake(Base.PISTACHE, Cream.VANILLE, Topping.BUENO)); //5.50
        cupcakes.add(new Cupcake(Base.CHOCOLAT, Cream.NUTELLA, Topping.BUENO)); //5.75
        cupcakes.add(new Cupcake(Base.NATURE, Cream.VANILLE, Topping.OREO)); //4.50
        // Cupcake du jour
        Cupcake cupcakeDuJour = new Cupcake(Base.NATURE, Cream.VANILLE, Topping.OREO); //2.7
        cupcakeDuJour.setCupDuJ();
        cupcakes.add(cupcakeDuJour);

        double coutAttendu = 0;
        for (Cupcake cupcake : cupcakes) {
            coutAttendu += cupcake.calculatePrice();
        }

        assertEquals(coutAttendu, factory.calculerPrixCommande(cupcakes), 0.001);
    }


    // Classe CupcakeFactory pour les tests
    class CupcakeFactory {
        public double calculerPrixCommande(List<Cupcake> cupcakes) {

            List<Cupcake> cupcakesNormaux = new ArrayList<>();
            List<Cupcake> cupcakesDuJour = new ArrayList<>();

            for (Cupcake cupcake : cupcakes) {
                if (cupcake.isCupDuJ()) {
                    cupcakesDuJour.add(cupcake);
                } else {
                    cupcakesNormaux.add(cupcake);
                }
            }

            double prixTotal = 0.0;

            for (Cupcake cupcake : cupcakesDuJour) {
                prixTotal += cupcake.calculatePrice();
            }

            if (cupcakesNormaux.size() >= 6) {
                cupcakesNormaux.sort((c1, c2) -> Double.compare(c1.calculatePrice(), c2.calculatePrice()));

                int nbCupcakesGratuits = cupcakesNormaux.size() / 6;

                for (int i = nbCupcakesGratuits; i < cupcakesNormaux.size(); i++) {
                    prixTotal += cupcakesNormaux.get(i).calculatePrice();
                }
            } else {
                for (Cupcake cupcake : cupcakesNormaux) {
                    prixTotal += cupcake.calculatePrice();
                }
            }

            return prixTotal;
        }
    }
}