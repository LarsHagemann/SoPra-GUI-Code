/**
 * 
 * @author Lars Hagemann
 * {@summary Kapselt eine Zutat fuer ein Rezept.}
 */
public class Zutat {
	/**
	 * {@summary Der Name der Zutat}
	 */
	protected String name;
	/**
	 * {@summary Die Menge, die von der Zutat benoetigt wird}
	 */
    protected int menge;
    /**
     * {@summary Die Einheit der Menge. z.B. g, ml, hcP. Keine Ahnung, was hcP ist.}
     */
    protected String einheit;
    

    /**
     * {@summary Erzeugt eine neue Zutat.}
     * @param zutatenname Name der Zutat
     * @param zutatenmenge Menge der Zutat
     * @param mengenEinheit Die Einheit der Menge, zum Beispiel g, kg, ml...
     */
    public Zutat(String zutatenname, int zutatenmenge, String mengenEinheit) {
        this.name = zutatenname;
        this.menge = zutatenmenge;
        this.einheit = mengenEinheit;
    }

    /**
     * {@summary Gibt die Zutat und Menge als String zurueck.}
     * @return Die Zutat als String
     */
    public String toString() {
        return menge + einheit + " " + name;
    }
    
    /**
     * {@summary gibt den Namen der Zutat zurueck}
     * @return Der Name der Zutat
     */
    public String getName() {
    	return name;
    }
    
    /**
     * {@summary gibt die Menge der Zutat zurueck}
     * @return Die Menge der Zutat
     */
    public int getMenge() {
    	return menge;
    }
    
    /**
     * @return Gibt die Einheit der Zutatenmenge zurueck
     */
    public String getEinheit() {
    	return einheit;
    }
}
