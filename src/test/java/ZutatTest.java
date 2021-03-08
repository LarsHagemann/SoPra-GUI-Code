import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Lars Hagemann
 * {@summary testet die Funktionalitaet der Zutat-Klasse}
 */
public class ZutatTest {
	/**
	 * {@summary testet, ob erstellte Zutaten nicht null sind}
	 */
	@Test public void testZutatNotNull() {
        var foo = new Zutat("Knoblauch", 10, "g");
        assertNotNull(foo);
    }
	/**
	 * {@summary testet, ob erstellte Zutaten die korrekten Werte haben.}
	 */
	@Test public void testZutatValid() {
		var zutat = new Zutat("Sellerie", 10, " Stangen");
		assertEquals(zutat.getMenge(), 10);
		assertEquals(zutat.getName(), "Sellerie");
	}
}
