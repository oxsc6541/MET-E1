package bar;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DatenmodellTest {

    @Test
    public void testZutatEquals() {
        Zutat z1 = new Zutat("Rum", true);
        Zutat z2 = new Zutat("rum", true);
        assertEquals(z1, z2);
    }

    @Test
    public void testCocktailAnleitung() {
        HashMap<Zutat, Double> rezept = new HashMap<>();
        rezept.put(new Zutat("Rum", true), 4.0);
        rezept.put(new Zutat("Cola", false), 6.0);
        Cocktail c = new Cocktail("Cuba Libre", rezept);

        String expectedStart = "Für einen Cuba Libre mische: ";
        assertTrue(c.getAnleitung().startsWith(expectedStart));
    }
}
