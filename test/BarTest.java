package bar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BarTest {

    private Bar bar;

    @BeforeEach
    public void setUp() {
        bar = new Bar();

        HashMap<Zutat, Double> mojito = new HashMap<>();
        mojito.put(new Zutat("Rum", true), 4.0);
        mojito.put(new Zutat("Minze", false), 1.0);
        bar.addCocktail(new Cocktail("Mojito", mojito));

        HashMap<Zutat, Double> ipanema = new HashMap<>();
        ipanema.put(new Zutat("Minze", false), 1.0);
        ipanema.put(new Zutat("Sodawasser", false), 5.0);
        bar.addCocktail(new Cocktail("Ipanema", ipanema));
    }

    @Test
    public void testFiltereRezepte_findetAlkoholfreie() {
        List<Cocktail> alkoholfrei = bar.filtereRezepte(
                c -> c.getRezept().keySet().stream().noneMatch(Zutat::isIstAlkoholisch)
        );
        assertEquals(1, alkoholfrei.size());
        assertEquals("Ipanema", alkoholfrei.get(0).getName());
    }

    @Test
    public void testFiltereRezepte_findetAlkoholische() {
        List<Cocktail> alkoholisch = bar.filtereRezepte(
                c -> c.getRezept().keySet().stream().anyMatch(Zutat::isIstAlkoholisch)
        );
        assertEquals(1, alkoholisch.size());
        assertEquals("Mojito", alkoholisch.get(0).getName());
    }

    @Test
    public void testSpeichernUndLaden() throws IOException {
        String pfad = "cocktails_test.txt";
        bar.speichereRezepte(pfad);

        Bar bar2 = new Bar();
        bar2.ladeRezepte(pfad);

        assertEquals(bar.getAnzahlCocktails(), bar2.getAnzahlCocktails());
        assertEquals(bar.getCocktails(), bar2.getCocktails());

        new File(pfad).delete();
    }

    @Test
    public void testGetAlkoholfreieCocktailsMitStreamApi() {
        List<Cocktail> alkoholfrei = bar.getAlkoholfreieCocktails();
        assertEquals(1, alkoholfrei.size());
        assertEquals("Ipanema", alkoholfrei.get(0).getName());
    }

    @Test
    public void testGetCocktailNamenMitMaxZutaten() {
        List<String> result = bar.getCocktailNamenMitMaxZutaten(2);
        assertEquals(List.of("Mojito", "Ipanema"), result);
    }
}
