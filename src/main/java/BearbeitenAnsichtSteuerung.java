/**
 * Diese Klasse dient der Steuerun von Bearbeitungsaufgaben
 * @author Lars Hagemann
 *
 */
public class BearbeitenAnsichtSteuerung {
	
	/**
	 * Instanz der Hauptklasse
	 */
	protected App app;
	/**
	 * Das Rezept, das bearbeitet wird
	 */
	protected Rezept rezept;
	/**
	 * Arbeitskopie des Originalrezeptes
	 */
	protected Rezept copyRezept;
	/**
	 * Grenzklasse
	 */
	protected BearbeitenAnsicht ansicht;
	
	/**
	 * Generiert eine neue Steuerungsklassen-Instanz
	 * @param app Instanz der Hauptklasse
	 * @param rezept Das Rezept, das bearbeitet werden soll
	 */
	public BearbeitenAnsichtSteuerung(App app, Rezept rezept) {
		this.app = app;
		this.rezept = rezept;
		this.copyRezept = new Rezept(rezept);
	}
	
	/**
	 * Stellt die Ansicht als Scene Root dar
	 */
	public void show() {
		ansicht = new BearbeitenAnsicht(copyRezept, this);
		app.getScene().setRoot(ansicht);
	}
	
	/**
	 * Laedt zurueck in die Hauptansicht
	 */
	public void ladeHauptseite() {
		if (!JUnitHelper.isJUnitTest()) // Ueberspringe in JUnit Tests
			new RezeptAnsichtSteuerung(app, rezept).show();
	}
	
	/**
	 * Diese Funktion wird aufgerufen, wenn der Benutzer auf "Speichern" klickt.
	 * Sie kuemmert sich darum, dass das Rezept auch wirklich gespeichert wird.
	 */
	public void handleSpeichern() {
		rezept.name = ansicht.getName();
		rezept.dauer = ansicht.getDauer();
		rezept.zutaten = ansicht.getZutaten();
		rezept.anweisungen = ansicht.getAnweisungen();
		ladeHauptseite();
	}
	
	/**
	 * entfernt eine Zutat aus dem Rezept
	 * @param zutat die zu entfernende Zutat
	 */
	public void entferneZutat(Zutat zutat) {
		copyRezept.zutatEntfernen(zutat);
		ansicht.ladeZutaten();
	}
	
	/**
	 * entfernt eine Anweisung aus der Anweisungsliste
	 * @param anweisungsNummer die Nummer der Anweisung
	 */
	public void entferneAnweisung(int anweisungsNummer) {
		copyRezept.anweisungEntfernen(anweisungsNummer - 1);
		ansicht.ladeAnweisungen();
	}
	
	/**
	 * Diese Funktion wird aufgerufen, wenn der Benutzer auf "Abbrechen" klickt.
	 * Sie bricht den Bearbeitungsvorgang ab.
	 */
	public void handleAbbrechen() {
		ladeHauptseite();
	}
	
}
