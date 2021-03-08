import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Testet die BearbeitenAnsichtSteuerung
 * @author Lars Hagemann
 *
 */
public class BearbeitenAnsichtSteuerungTest {
	@Test
	public void testeHandleAbbrechen() {
		App app = new App(null);
		var rezept = new Rezept("", 0, null, null);
		var controller = new BearbeitenAnsichtSteuerung(app, rezept);
		controller.copyRezept.name = "ABCD";
		controller.copyRezept.dauer = 10;
		controller.copyRezept.anweisungHinzufuegen("ABCD", 0);
		controller.copyRezept.zutatHinzufuegen(new Zutat("Knoblauch", 1, " Zehe"), 0);
		controller.handleAbbrechen();
		assertNotEquals(rezept.name, controller.copyRezept.name);
		assertNotEquals(rezept.dauer, controller.copyRezept.dauer);
		assertNotEquals(rezept.zutaten, controller.copyRezept.zutaten);
		assertNotEquals(rezept.anweisungen, controller.copyRezept.anweisungen);
	}
}
