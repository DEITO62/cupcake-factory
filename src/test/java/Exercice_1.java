import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.example.*;
public class Exercice_1 {
    class ServiceMenuTest {

        private Base baseVanille = new Base("Base Vanille", 1.0);
        private Cream creamChocolat = new Cream("Crème Chocolat", 0.8);
        private Topping vermicelles = new Topping("Vermicelles", 0.2);

        private CupDuJ special1;
        private CupDuJ special2;

        private Stock initialiserStock() {
            // stock ingrédients
            ingr.put(baseVanille, 2);
            ingr.put(creamChocolat, 2);
            ingr.put(vermicelles, 1);

            // stock cupcakes du jour
            special1 = new CupDuJ("Choco Délice",
                    new Cupcake(baseVanille, creamChocolat, List.of(vermicelles)), 1);
            special2 = new CupDuJ("Rêve Vanille",
                    new Cupcake(baseVanille, creamChocolat, List.of()), 1);

            return new Stock(ingr, cupcakes);
        }

        @Test
        void menuContientCupcakesDuJour() {
            Stock stock = initialiserStock();
            Menu menu = new ServiceMenu(stock).genererMenu();
            assertTrue(menu.getCupcakesDuJour().contains(special1));
            assertTrue(menu.getCupcakesDuJour().contains(special2));
        }

        @Test
        void menuContientListeIngredients() {
            Stock stock = initialiserStock();
            Menu menu = new ServiceMenu(stock).genererMenu();
            assertTrue(menu.getIngredientsDisponibles().contains(fondVanille));
            assertTrue(menu.getIngredientsDisponibles().contains(cremeChocolat));
            assertTrue(menu.getIngredientsDisponibles().contains(vermicelles));
        }

        @Test
        void ingrédientÉpuiséEstRetiréDuMenu() {
            Stock stock = initialiserStock();
            stock.diminuerStockIngredient(vermicelles);  // stock → 0
            Menu menu = new ServiceMenu(stock).genererMenu();
            assertFalse(menu.getIngredientsDisponibles().contains(vermicelles));
        }

        @Test
        void cupcakeDuJourÉpuiséEstRetiréDuMenu() {
            Stock stock = initialiserStock();
            special1.diminuerStock();                    // stock → 0
            Menu menu = new ServiceMenu(stock).genererMenu();
            assertFalse(menu.getCupcakesDuJour().contains(special1));
            assertTrue(menu.getCupcakesDuJour().contains(special2));
        }

        @Test
        void quandPlusDeFondNiDeCrèmeSeulementCupcakesDuJour() {
            Stock stock = initialiserStock();
            // épuiser toutes les bases et crèmes
            stock.diminuerStockIngredient(fondVanille);
            stock.diminuerStockIngredient(fondVanille);
            stock.diminuerStockIngredient(cremeChocolat);
            stock.diminuerStockIngredient(cremeChocolat);

            Menu menu = new ServiceMenu(stock).genererMenu();
            assertTrue(menu.getIngredientsDisponibles().isEmpty());
            assertFalse(menu.getCupcakesDuJour().isEmpty());
        }

}