package bar;

public abstract class Getraenk {
    protected String name;

    public Getraenk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getAnleitung();
}
