import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Lars Hagemann
 * {@summary testet die Funktionalitaet des Rezeptbuchs}
 */
public class RezeptbuchTest {
	/**
	 * {@summary Tested, ob das Erstellen von neuen Rezeptbüchern funktioniert.}
	 */
    @Test public void rezeptbuchNotNull() {
    	Rezeptbuch rb = new Rezeptbuch(null);
    	assertNotNull(rb);
    	assertNotNull(rb.getRezepte());
    }
    /**
     * {@summary Testet, ob das hinzufuegen und entfernen von Rezepten funktioniert}
     */
    @Test public void rezepteHinzufuegenEntfernen() {
    	Rezept rezept = new Rezept("Leckeres Rezept", 100, null, null);
		rezept.anweisungHinzufuegen("Als zweites den Koch auf den Herd ablegen", 0);
		rezept.zutatHinzufuegen(new Zutat("Knoblauch", 10, "g"), 0);
		
    	Rezeptbuch rb = new Rezeptbuch(null);
    	assertNotNull(rb);
    	assertNotNull(rb.getRezepte());
    	
    	rb.rezeptHinzufuegen(rezept);
    	rb.rezeptHinzufuegen(rezept);
    	
    	assertEquals(rb.getRezepte().size(), 2);
    	
    	rb.rezeptEntfernen(rezept);
    	assertEquals(rb.getRezepte().size(), 1);
    }
}
