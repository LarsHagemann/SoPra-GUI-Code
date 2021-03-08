import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Grenzklasse zum Darstellen eines Rezeptes. Ausserdem Hauptseite der App.
 * @author Lars Hagemann
 */
public class RezeptAnsicht extends ScrollPane {
	
	/**
	 * Diese ComboBox laesst den Benutzer das Rezept auswaehlen, dass er angezeigt haben moechte.
	 */
	protected ComboBox<String> cmb_rezeptAuswahl;
	/**
	 * Dieser Text stellt die Zutaten fuer das Rezept dar.
	 */
	protected Label txt_zutaten;
	/**
	 * Dieser Text stellt die Anweisungsschritte eines Rezeptes dar.
	 */
	protected Label txt_anweisungen;
	/**
	 * Button zum Bearbeiten eines Rezeptes
	 */
	protected Button btn_bearbeiten;
	/**
	 * Referenz zum Rezeptbuch
	 */
	protected Rezeptbuch rezeptBuch;
	
	/**
	 * Das "Neues Rezept Erstellen"-Item
	 */
	protected final String s_neuesRezept = "-- Neues Rezept --";
	/**
	 * Das "Nichts ausgewaehlt"-Item
	 */
	protected final String s_rezeptAuswaehlen = "-- Rezept Auswaehlen --";
	/**
	 * Controller dieser Ansicht
	 */
	protected RezeptAnsichtSteuerung controller;
	/**
	 * Label zum Darstellen der Zubereitungszeit
	 */
	protected Label lbl_zeit;
	
	/**
	 * {@summary Grenzklasse zum Darstellen der Hauptseite der App.}
	 * @param rezeptBuch
	 */
	RezeptAnsicht(Rezeptbuch rezeptBuch, RezeptAnsichtSteuerung controller) {
		super();
		this.rezeptBuch = rezeptBuch;
		this.controller = controller;
		
		cmb_rezeptAuswahl = new ComboBox<String>();
		btn_bearbeiten = new Button("Bearbeiten");
		txt_zutaten = new Label();
		txt_anweisungen = new Label();
		lbl_zeit = new Label();
		
		cmb_rezeptAuswahl.setId("cmb-select-rezept");
		var scene = controller.getApp().getScene();
		cmb_rezeptAuswahl.prefWidthProperty().bind(scene.widthProperty());
		
		btn_bearbeiten.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				var rezept = cmb_rezeptAuswahl.getValue();
				if (rezept == null || rezept.equals(s_rezeptAuswaehlen)) {
					System.out.println("Kein Rezept zum Bearbeiten ausgewaehlt!");
				} else if (rezept.equals(s_neuesRezept)) {
					controller.neuesRezeptRegistrieren();
				} else {
					controller.bearbeiteRezept(rezept);
				}
			}
		});
		
		cmb_rezeptAuswahl.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
                System.out.println(cmb_rezeptAuswahl.getValue() + " selected!");
                
                var value = cmb_rezeptAuswahl.getValue();
                if (value == null || value.equals(s_rezeptAuswaehlen)) {
                	// kein Rezept ausgewaehlt
                } else if (value.equals(s_neuesRezept)) {
                	// neues Rezept erstellen
                	controller.neuesRezeptRegistrieren();
                } else {
                	// rezept laden...
                	var rezept = rezeptBuch.rezeptFromName(value);
                	ladeRezept(rezept);
                }
            }
		});
		
		ladeRezepteListe();
		txt_zutaten.setPadding(new Insets(10, 0, 0, 0));
		txt_anweisungen.setPadding(new Insets(10, 0, 10, 0));
		
		var center = new VBox();
		center.getChildren().addAll(lbl_zeit, txt_zutaten, txt_anweisungen);
		center.setPadding(new Insets(10, 10, 10, 10));
		
		var mainBox = new VBox(cmb_rezeptAuswahl, center, btn_bearbeiten);
		
		setContent(mainBox);
		//setTop(cmb_rezeptAuswahl);
		//setCenter(center);
		//setBottom(btn_bearbeiten);		
	}
	
	/**
	 * Generiert die ComboBox, die intern genutzt wird.
	 */
	void ladeRezepteListe() {
		cmb_rezeptAuswahl.getItems().clear();
		
		cmb_rezeptAuswahl.getItems().addAll(
			s_rezeptAuswaehlen,
			s_neuesRezept
		);
		for (var rezept : rezeptBuch.getRezepte()) {
			cmb_rezeptAuswahl.getItems().add(rezept.getName());
		}
		
		cmb_rezeptAuswahl.setValue(s_rezeptAuswaehlen);
	}
	
	/**
	 * Laedt den Inhalt eines bestimmten Rezeptes
	 * @param rezept Das zu ladende Rezept
	 */
	protected void ladeRezept(Rezept rezept) {
		cmb_rezeptAuswahl.setValue(rezept.getName());
		lbl_zeit.setText("Zubereitungszeit: " + rezept.getDauer() + "min");
		ladeZutatenListe(rezept);
		ladeAnweisungsListe(rezept);
	}
	
	/**
	 * Hilfsfunktion um die Zutatenliste eines Rezeptes zu laden.
	 * @param rezept das zu ladende Rezept
	 */
	protected void ladeZutatenListe(Rezept rezept) {
		String zutaten = "";
		for (var zutat : rezept.getZutaten()) {
			zutaten += zutat.toString() + "\n";
		}
		txt_zutaten.setText(zutaten);
	}
	
	/**
	 * Hilfsfunktion um die Anweisungsliste eines Rezeptes zu laden.
	 * @param rezept das zu ladende Rezept
	 */
	protected void ladeAnweisungsListe(Rezept rezept) {
		var a_str = "";
		var anweisungen = rezept.getAnweisungen();
		
		for (int i = 1; i <= anweisungen.size(); ++i) {
			a_str += i + ". " + anweisungen.get(i - 1) + "\n\n";
		}
		
		txt_anweisungen.setText(a_str);
	}
}
