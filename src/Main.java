package bar;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar();

        HashMap<Zutat, Double> rezept1 = new HashMap<>();
        rezept1.put(new Zutat("Cachaca", true), 5.0);
        rezept1.put(new Zutat("Limettensaft", false), 2.0);
        bar.addCocktail(new Cocktail("Caipirinha", rezept1));

        HashMap<Zutat, Double> rezept2 = new HashMap<>();
        rezept2.put(new Zutat("Minze", false), 1.0);
        rezept2.put(new Zutat("Sodawasser", false), 6.0);
        bar.addCocktail(new Cocktail("Ipanema", rezept2));

        bar.printMenu();
    }
}
