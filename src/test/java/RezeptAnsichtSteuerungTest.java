import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testet die RezeptAnsichtSteuerung
 * @author Lars Hagemann
 *
 */
public class RezeptAnsichtSteuerungTest {
	@Test
	public void testeRezeptAnsicht() {
		App app = new App();
		Rezept rezept = new Rezept("", 0, null, null);
		var controller = new RezeptAnsichtSteuerung(app, rezept);
		assertTrue(controller.getApp() == app);
		var anzahlRezepte = app.getRezeptbuch().getRezepte().size();
		controller.neuesRezeptRegistrieren();
		assertEquals(app.getRezeptbuch().getRezepte().size(), anzahlRezepte + 1);
	}
}
