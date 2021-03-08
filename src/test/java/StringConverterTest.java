import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testet die Implementation des StringConverter
 * @author Lars Hagemann
 *
 */
public class StringConverterTest {
	@Test
	public void testeKorrekteEnkodierung()  {
		var conv = new StringConverter();
		assertEquals(conv.toBase64("abcd"), "YWJjZA==");
	}
	
	@Test 
	public void testeKorrekteDekodierung() {
		var conv = new StringConverter();
		assertEquals(conv.fromBase64("YWJjZA=="), "abcd");
	}
	
	@Test
	public void testeNurString() {
		// Unser Converter soll nur Strings enkodieren und keine anderen Klassen
		var conv = new StringConverter();
		assertTrue(conv.canConvert(String.class));
		assertFalse(conv.canConvert(Integer.class));
		assertFalse(conv.canConvert(Rezeptbuch.class));
	}
}
