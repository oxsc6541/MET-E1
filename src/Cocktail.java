package bar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cocktail extends Getraenk {
    private final HashMap<Zutat, Double> rezept;

    public Cocktail(String name, HashMap<Zutat, Double> rezept) {
        super(name);
        this.rezept = rezept;
    }

    public HashMap<Zutat, Double> getRezept() {
        return rezept;
    }

    @Override
    public String getAnleitung() {
        StringBuilder builder = new StringBuilder("Für einen " + name + " mische: ");
        for (Map.Entry<Zutat, Double> entry : rezept.entrySet()) {
            builder.append(entry.getValue()).append("cl ").append(entry.getKey().getName()).append(", ");
        }
        return builder.substring(0, builder.length() - 2); // letztes Komma entfernen
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail)) return false;
        Cocktail cocktail = (Cocktail) o;
        return name.equalsIgnoreCase(cocktail.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
