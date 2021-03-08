/**
 * Hilfsfunktionen zum Testen
 * @author Lars Hagemann
 */
public class JUnitHelper {
	/**
	 * Test Reflektion
	 * https://stackoverflow.com/questions/2341943/how-can-i-find-out-if-code-is-running-inside-a-junit-test-or-not
	 * @return true, wenn ein Test laeuft, false sonst
	 */
	public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  	if (element.getClassName().startsWith("org.junit.")) {
	    		return true;
	    	}           
	  	}
	  	return false;
	}
}
