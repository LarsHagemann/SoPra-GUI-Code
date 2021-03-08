/**
 * Steuerungsklasse fuer die Hauptseite der App
 * @author Lars Hagemann
 *
 */
public class RezeptAnsichtSteuerung {
	
	/**
	 * Die Ansicht fuer das Rezept
	 */
	protected RezeptAnsicht ansicht;
	/**
	 * Hauptklasse der Anwendung
	 */
	protected App app;
	
	/**
	 * Konstruktor der Steuerungsklasse
	 * @param app Instanz der Hauptklasse
	 * @param rezept Rezept, dass geladen werden soll. Kann null sein.
	 */
	public RezeptAnsichtSteuerung(App app, Rezept rezept) {
		this.app = app;
		
		if (!JUnitHelper.isJUnitTest())
		{
			ansicht = new RezeptAnsicht(app.rezeptBuch, this);
			if (rezept != null) {
				ansicht.ladeRezept(rezept);
			}
		}
	}
	
	/**
	 * Stellt die Ansicht als Scene Root dar.
	 */
	public void show() {
		app.getScene().setRoot(ansicht);
	}
	
	/**
	 * Getter fuer die App Instanz
	 * @return die app Instanz
	 */
	public App getApp() {
		return app;
	}
	
	/**
	 * Erstellt ein neues Rezept und oeffnet die Bearbeiten Ansicht
	 */
	public void neuesRezeptRegistrieren() {
		Rezept rezept = new Rezept("", 0, null, null);
		app.getRezeptbuch().rezeptHinzufuegen(rezept);
		if (app.getScene() != null)
			bearbeiteRezept(rezept); // Nur, wenn wir nicht testen
	}
	
	/**
	 * Laedt ein bestehendes Rezept in die Bearbeiten Ansicht
	 * @param rezeptName: Der Name des zu ladenden Rezeptes
	 */
	public void bearbeiteRezept(String rezeptName) {
		for (var rezept : app.getRezeptbuch().getRezepte()) {
			if (rezept.getName() == rezeptName) {
				bearbeiteRezept(rezept);
				return;
			}
		}
		System.out.println("Rezept \"" + rezeptName + "\" nicht gefunden!");
	}
	
	/**
	 * Laedt ein bestehendes Rezept in die Bearbeiten Ansicht
	 * @param rezept: Das zu ladende Rezept
	 */
	protected void bearbeiteRezept(Rezept rezept) {
		if (!JUnitHelper.isJUnitTest()) // Nur machen, wenn kein Unit Test laeuft
			new BearbeitenAnsichtSteuerung(app, rezept).show();
	}
	
}
