import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * {@summary Ein Rezeptbuch in dem beliebig viele Rezepte gespeichert werden koennen.}
 * @author Lars Hagemann
 */
public class Rezeptbuch {

	/**
	 * {@summary Die Liste aller Rezepte im Rezeptbuch}
	 */
    protected List<Rezept> rezepte;
    
    /**
     * {@summary Getter fuer Rezeptbuch.rezepte}
     * @return Gibt das Attribut Rezeptbuch.rezepte zurueck
     */
    public List<Rezept> getRezepte() {
    	return rezepte;
    }

    /**
     * {@summary Erzeugt ein neues Rezeptbuch}
     * @param rezepte die Liste der Rezepte im Rezeptbuch
     */
    public Rezeptbuch(List<Rezept> rezepte) {
        this.rezepte = rezepte;
        if (this.rezepte == null)
        	this.rezepte = new ArrayList<Rezept>();
    }
    
    /**
     * Default Konstruktor fuer Rezeptbuecher
     */
    public Rezeptbuch() {
        this.rezepte = new ArrayList<Rezept>();
    }

    /**
     * {@summary fuegt dem Rezeptbuch ein neues Rezept hinzu}
     * @param rezept das hinzuzufuegende Rezept
     */
    public void rezeptHinzufuegen(Rezept rezept) {
    	this.rezepte.add(rezept);
    }

    /**
     * @param rezept das zu entfernende Rezept
     */
    public void rezeptEntfernen(Rezept rezept) {
    	this.rezepte.remove(rezept);
    }

    /**
     * {@summary gibt das Rezeptbuch als String zurueck}
     */
    public String toString() {
    	String rezeptbuch = "Rezeptbuch: \n";
    	for (Rezept rezept : rezepte) {
    		rezeptbuch = rezeptbuch + rezept.toString() + "\n";
    	}
    	return rezeptbuch;
    }
    
    /**
     * Laedt einen XStream so, dass er Rezeptbuecher (de-)serialisieren kann
     * @return den angepassten XStream
     */
    public static XStream ladeXStream() {
    	var stream = new XStream(new DomDriver());
    	XStream.setupDefaultSecurity(stream);
    	stream.allowTypes(new Class<?>[] { 
    		String.class, 
    		Integer.class, 
    		Rezeptbuch.class, 
    		Rezept.class, 
    		Zutat.class,
    		List.class,
    		ArrayList.class,
    		TreeMap.class
    	});
    	stream.alias("Rezeptbuch", Rezeptbuch.class);
    	stream.alias("Rezept", Rezept.class);
    	stream.alias("Zutat", Zutat.class);
    	stream.registerConverter(new StringConverter());
    	return stream;
    }
    
    /**
     * Laedt das Rezeptbuch in eine Datei
     * @param dateiPfad der Pfad zur Zieldatei
     * @return true, falls das laden in die Datei funktioniert hat, sonst false
     */
    public boolean ladeInDatei(String dateiPfad) {
    	var stream = ladeXStream();
    	var xml = stream.toXML(this);
    	
    	try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(dateiPfad));
	        writer.write(xml);
	        writer.close();
	        return true;
    	}
    	catch(IOException e) {
    		System.out.println("Datei konnte nicht geschrieben werden!");
    	}
    	return false;
    }
    
    /**
     * Versucht ein Rezept anhand seines Namens zu indentifizieren
     * @param rezeptName Der Name des Rezeptes
     * @return Das zugeoerige Rezept oder, falls nicht gefunden, null
     */
    public Rezept rezeptFromName(String rezeptName) {
    	for (var rezept : rezepte) {
    		if (rezept.getName().equals(rezeptName))
    			return rezept;
    	}
    	return null;
    }
}
