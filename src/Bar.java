package bar;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Bar {
    private final ArrayList<Cocktail> cocktails = new ArrayList<>();

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    public int getAnzahlCocktails() {
        return cocktails.size();
    }

    public ArrayList<Cocktail> getCocktails() {
        return cocktails;
    }

    public void printMenu() {
        if (cocktails.isEmpty()) {
            System.out.println("Die Bar hat noch keine Cocktails.");
        } else {
            System.out.println("Cocktail-Menü:");
            for (Cocktail c : cocktails) {
                System.out.println("- " + c.getName());
                System.out.println("  " + c.getAnleitung());
            }
        }
    }

    public void speichereRezepte(String dateiPfad) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiPfad))) {
            for (Cocktail cocktail : cocktails) {
                StringBuilder zeile = new StringBuilder();
                zeile.append(cocktail.getName());
                for (Zutat z : cocktail.getRezept().keySet()) {
                    double menge = cocktail.getRezept().get(z);
                    zeile.append(";").append(z.getName()).append(",")
                            .append(menge).append(",").append(z.isIstAlkoholisch());
                }
                writer.write(zeile.toString());
                writer.newLine();
            }
        }
    }

    public void ladeRezepte(String dateiPfad) throws IOException {
        cocktails.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(dateiPfad))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                String name = teile[0];
                HashMap<Zutat, Double> rezept = new HashMap<>();
                for (int i = 1; i < teile.length; i++) {
                    String[] zutatInfos = teile[i].split(",");
                    String zutatName = zutatInfos[0];
                    double menge = Double.parseDouble(zutatInfos[1]);
                    boolean istAlkoholisch = Boolean.parseBoolean(zutatInfos[2]);
                    rezept.put(new Zutat(zutatName, istAlkoholisch), menge);
                }
                cocktails.add(new Cocktail(name, rezept));
            }
        }
    }

    public ArrayList<Cocktail> filtereRezepte(RezeptFilter filter) {
        ArrayList<Cocktail> gefiltert = new ArrayList<>();
        for (Cocktail c : cocktails) {
            if (filter.teste(c)) {
                gefiltert.add(c);
            }
        }
        return gefiltert;
    }

    public List<Cocktail> getAlkoholfreieCocktails() {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getRezept().keySet().stream()
                        .noneMatch(Zutat::isIstAlkoholisch))
                .collect(Collectors.toList());
    }

    public List<String> getCocktailNamenMitMaxZutaten(int maxAnzahl) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getRezept().size() <= maxAnzahl)
                .map(Cocktail::getName)
                .collect(Collectors.toList());
    }
}

