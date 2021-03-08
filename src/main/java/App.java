import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Die Klasse App kuemmert sich um das Darstellen und verwalten unseres Rezeptbuchs.
 * Ausserdem ist sie die Steuerungsklasse fuer das Darstellen von Rezepten.
 * @author Lars Hagemann
 * TODO:
 * 	GUI schoen machen
 */
public class App extends Application {
	/**
	 * Dateipfad zur Rezeptbuchdatei
	 */
	public static final String s_filepath = "rezeptbuch.xml";
	/**
	 * Dateipfad fuer das css
	 */
	public static final String s_csspath = "/rezeptbuch.css";
	/**
	 * Die Hauptscene der Anwendung
	 */
	protected Scene scn_haupt;
	/**
	 * Rezeptbuch Instanz
	 */
	protected Rezeptbuch rezeptBuch;
	
	/**
	 * @return the main scene
	 */
	public Scene getScene() {
		return scn_haupt;
	}
	
	/**
	 * @return die Rezeptbuch Instanz 
	 */
	public Rezeptbuch getRezeptbuch() {
		return rezeptBuch;
	}
	
	/**
	 * Default Konstruktor, der von dem Programm benutzt wird
	 */
	public App() {
		var stream = Rezeptbuch.ladeXStream();
		var file = new File(s_filepath);
		if (file.exists() && !JUnitHelper.isJUnitTest()) {
			// Nur aus Datei laden, falls Datei nicht existiert und wir nicht testen
			rezeptBuch = (Rezeptbuch) stream.fromXML(file);
		} else {
			rezeptBuch = new Rezeptbuch();
		}
	}
	
	/**
	 * Dieser Konstruktor wird von der Test-Engine genutzt, damit diese nicht
	 * den Inhalt der Datei veraendert
	 * @param unimportantParameter unwichtiger Parameter, um die zwei Konstruktoren zu unterscheiden
	 */
	public App(String unimportantParameter) {
		rezeptBuch = new Rezeptbuch();
	}

	/**
	 * Programmeinstiegspunkt fuer unsere Rezeptbuch Anwendung.
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Lars' Rezeptbuch");
		scn_haupt = new Scene(new BorderPane(), 600, 500);
        primaryStage.setScene(scn_haupt);
		primaryStage.show();
		new RezeptAnsichtSteuerung(this, null).show();
	}

	/**
	 * wird beim Ende des Programms aufgerufen
	 */
	@Override
	public void stop() {
		rezeptBuch.ladeInDatei(s_filepath);
	}
}
