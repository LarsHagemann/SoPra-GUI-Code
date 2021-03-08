import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Test Implementationen fuer die App-Hauptklasse
 * @author Klausuren
 *
 */
public class AppTest {
	 @Test
	 public void testNotNull() {
		 var app0 = new App();
		 assertNotNull(app0);
		 assertNotNull(app0.getRezeptbuch());
		 
		 var app1 = new App();
		 assertNotNull(app1);
		 assertNotNull(app1.getRezeptbuch());
	 }
}
