import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * {@summary Testet die Funktionalitaet der Rezept-Klasse}
 * @author Klausuren
 *
 */
public class RezeptTest {
	/**
	 * {@summary Testet, ob das Erstellen von neuen Rezepten funktioniert}
	 */
	@Test public void testRezeptNotNull() {
		Rezept rezept = new Rezept("Leeres Rezept", 0, null, null);
		assertNotNull(rezept);
		assertNotNull(rezept.getAnweisungen());
		assertNotNull(rezept.getZutaten());
	}
	/**
	 * {@summary Testet, ob der Name eines Rezeptes richtig verarbeitet wird.}
	 */
	@Test public void testRezeptName() {
		Rezept rezept = new Rezept("Leckeres Rezept", 0, null, null);
		assertEquals(rezept.getName(), "Leckeres Rezept");
	}
	/**
	 * {@summary Testet, ob die Dauer fuer ein Rezept richtig verarbeitet wird}
	 */
	@Test public void testRezeptDauer() {
		Rezept rezept = new Rezept("Leckeres Rezept", 100, null, null);
		assertEquals(rezept.getDauer(), 100);
	}
	/**
	 * {@summary Testet, ob das hinzufuegen und entfernen von Zutaten richtig funktioniert.}
	 */
	@Test public void testZutatHinzufuegenEntfernen() {
		Rezept rezept = new Rezept("Leckeres Rezept", 100, null, null);
		rezept.zutatHinzufuegen(new Zutat("Knoblauch", 10, "g"), 0);
		rezept.zutatHinzufuegen(new Zutat("Karamelsirup", 10, "ml"), 0);
		
		assertEquals(rezept.getZutaten().size(), 2);
		rezept.zutatEntfernen(0);
		assertEquals(rezept.getZutaten().size(), 1);
	}
	/**
	 * {@summary Testet, ob das hinzufuegen und entfernen von Anweisungen richtig funktioniert.}
	 */
	@Test public void testAnweisungenHinzufuegenEntfernen() {
		Rezept rezept = new Rezept("Leckeres Rezept", 100, null, null);
		rezept.anweisungHinzufuegen("Als zweites den Koch auf den Herd ablegen", 0);
		rezept.anweisungHinzufuegen("Als erstes den Koch gut in Semmelbrösel wenden", 0);
		
		assertEquals(rezept.getAnweisungen().size(), 2);
		rezept.anweisungEntfernen(0);
		assertEquals(rezept.getAnweisungen().size(), 1);
	}
}
