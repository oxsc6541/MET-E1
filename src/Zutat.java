package bar;

import java.util.Objects;

public class Zutat {
    private final String name;
    private final boolean istAlkoholisch;

    public Zutat(String name, boolean istAlkoholisch) {
        this.name = name;
        this.istAlkoholisch = istAlkoholisch;
    }

    public String getName() {
        return name;
    }

    public boolean isIstAlkoholisch() {
        return istAlkoholisch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zutat)) return false;
        Zutat zutat = (Zutat) o;
        return name.equalsIgnoreCase(zutat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
