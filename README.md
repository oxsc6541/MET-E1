# MET-E1: Metriken 

Dieses Repository enthält das Java-Projekt „Cocktailbar“ (eine alte Java-Übungsdatei)
## Projektaufbau & Werkzeuge

Das Projekt wurde von einem reinen IDE-Projekt in ein **Apache Maven**-Projekt migriert. Zur statischen Codeanalyse sind folgende Werkzeuge integriert:

1. **PMD (Version 3.21.2)**: Analyse der Code-Qualität und Einhaltung von Java-Richtlinien.
2. **JDepend (Version 2.0)**: Analyse von Paket-Abhängigkeiten und Architektur-Metriken ($C_a$, $C_e$, $A$, $I$).

---

##  Besondere Herausforderungen & Lösungen (Debugging)

Im Laufe der Einrichtung wurden die Probleme analysiert und behoben:

* **Fehlender Maven-Pfad unter Windows**: Der Befehl `mvn` war global nicht registriert. Die Ausführung erfolgt daher zuverlässig über die integrierte Maven-GUI in IntelliJ IDEA.
* **Absturz von JDepend (`target/classes` fehlt)**: JDepend benötigt kompilierten Bytecode. Die Ausführung erfordert den vorherigen Aufruf des `compile`-Lifecycles.
* **Abweichende Ordnerstruktur**: Da der Quellcode in einer flachen Struktur direkt unter `src/` liegt (statt dem Maven-Standard `src/main/java`), wurde die `pom.xml` explizit angepasst:
  ```xml
  <sourceDirectory>src</sourceDirectory>
  ```

---

##  Manuelle Metrik-Berechnung (McCabe)

Für die Methode `printMenu()` in der Klasse `Bar.java` wurde die zyklomatische Komplexität händisch berechnet:
* **Basiswert:** 1
* **Entscheidungspunkte:** +1 (`if`), +1 (`for`-Schleife)
* **Ergebnis:** Zyklomatische Komplexität $V(G) = 3$.

---

## Ausführung der Tools

Die Metriken können in IntelliJ über das Maven-Tool-Fenster oder über das Terminal wie folgt generiert werden:

```bash
# Projekt bereinigen und kompilieren
mvn clean compile

# PMD Report generieren (target/site/pmd.html)
mvn pmd:pmd

# JDepend Report generieren (target/jdepend-report.xml)
mvn jdepend:generate
```
## KI-Benutzung: 
Nacharbeiten vom Bericht, Fehlersuche, Strukturierung, Korrigieren, Ergänzung von README
