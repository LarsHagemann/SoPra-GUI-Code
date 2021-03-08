import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lars Hagemann
 * {@summary Diese Klasse kapselt ein Rezept. Ein Rezept enthaelt dementsprechend
 * 	einen Namen, eine Kochdauer, eine Zutatenliste und mehrere Anweisungen.}
 */
public class Rezept {
	/**
	 * {@summary Der Name des Rezeptes}
	 */
	protected String name;
	/**
	 * {@summary Die Zubereitungsdauer}
	 */
    protected int dauer;
    /**
     * {@summary Die Liste der Zutaten}
     */
    protected List<Zutat> zutaten;
    /**
     * {@summary Die Liste der Anweisungen}
     */
    protected List<String> anweisungen;
    
    /**
     * {@summary Getter fuer Rezept.name}
     * @return Gibt das Attribut Rezept.name zurueck
     */
    public String getName() {
    	return name;
    }
    
    /**
     * {@summary Getter fuer Rezept.dauer}
     * @return Gibt das Attribut Rezept.dauer zurueck
     */
    public int getDauer() {
    	return dauer;
    }
    
    /**
     * {@summary Getter fuer Rezept.zutaten}
     * @return Gibt das Attribut Rezept.zutaten zurueck
     */
    public List<Zutat> getZutaten() {
    	return zutaten;
    }
    
    /**
     * {@summary Getter fuer Rezept.anweisungen}
     * @return Gibt das Attribut Rezept.anweisungen zurueck
     */
    public List<String> getAnweisungen() {
    	return anweisungen;
    }

    /**
     * {@summary Erzeugt ein neues Rezept}
     * @param rezeptname Name des Rezeptes
     * @param rezeptdauer Kochdauer des Rezeptes
     * @param rezeptzutaten Die Liste der Zutaten des Rezeptes
     * @param rezeptanweisungen Durchfuehranweisungen des Rezeptes
     */
    public Rezept(String rezeptname, int rezeptdauer, List<Zutat> rezeptzutaten, List<String> rezeptanweisungen) {
        this.name = rezeptname;
        this.dauer = rezeptdauer;
        this.zutaten = rezeptzutaten;
        this.anweisungen = rezeptanweisungen;
        if (this.zutaten == null) // Keine Liste uebergeben
        	this.zutaten = new ArrayList<Zutat>();
        if (this.anweisungen == null) // Keine Anweisungen uebergeben
        	this.anweisungen = new ArrayList<String>();
        
    }
    
    /**
     * Copy-Konstruktor fuer Rezepte
     * @param other Das Rezept, das kopiert werden soll
     */
    public Rezept(Rezept other) {
    	this.name = other.getName();
    	this.dauer = other.getDauer();
    	this.zutaten = new ArrayList<Zutat>(other.getZutaten());
        this.anweisungen = new ArrayList<String>(other.getAnweisungen());
        if (this.zutaten == null) // Keine Liste uebergeben
        	this.zutaten = new ArrayList<Zutat>();
        if (this.anweisungen == null) // Keine Anweisungen uebergeben
        	this.anweisungen = new ArrayList<String>();
    }

    /**
     * {@summary fuegt dem Rezept eine neue Zutat an einer bestimmten Stelle des Rezeptes hinzu.}
     * @param zutat die Zutat die hinzugefuegt wird 
     * @param position die Position an der die Zutat hinzugefuegt werden soll
     */
    public void zutatHinzufuegen(Zutat zutat, int position) {
    	this.zutaten.add(position, zutat);
    }

    /**
     * {@summary entfernt eine Zutat aus der Zutatenliste.}
     * @param position Die Position der Zutat, die entfernt werden soll
     */
    public void zutatEntfernen(int position) {
    	this.zutaten.remove(position);
    }
    
    /**
     * Entfernt eine Zutat by Reference
     * @param zutat die zu entfernende Zutat
     */
    public void zutatEntfernen(Zutat zutat) {
    	this.zutaten.remove(zutat);
    }

    /**
     * {@summary fuegt dem Rezept eine neue Anweisung an einer bestimmten Stelle hinzu}
     * @param anweisung neue Anweisung fuer das Rezept
     * @param position Position der neuen Anweisung im Rezept
     */
    public void anweisungHinzufuegen(String anweisung, int position) {
    	this.anweisungen.add(position, anweisung);
    }

    /**
     * {@summary entfernt eine Anweisung aus dem Rezept.}
     * @param position Entfernt eine Anweisung aus der Liste
     */
    public void anweisungEntfernen(int position) {
    	this.anweisungen.remove(position);
    }

    /**
     * @return Das Rezept umgewandelt in einen String zum einfachen Darstellen
     */
    public String toString() {
    	String rezept = "Name: " + name + "\n" + "Dauer: " + dauer + "\n";
    	rezept = rezept + "Zutaten: \n";
    	for (Zutat zutat : zutaten) {
    		rezept = rezept + zutat.toString() + "\n";
    	}
    	rezept = rezept + "Anweisungen: \n";
    	for (String anweisung : anweisungen) {
    		rezept = rezept + anweisung + "\n";
    	}
    	return rezept;
    }
}
