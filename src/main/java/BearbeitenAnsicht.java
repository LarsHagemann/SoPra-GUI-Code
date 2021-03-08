import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Grenzklasse zum Darstellen der Bearbeitungsfunktion von Rezepten
 * @author Lars Hagemann
 */
public class BearbeitenAnsicht extends ScrollPane {
	/**
	 * Innere Klasse zum Darstellen einer Bearbeitungsbox fuer Zutaten.
	 * @author Lars Hagemann
	 */
	protected class ZutatenBearbeitung extends HBox {
		/**
		 * Textfeld fuer den Namen der Zutat
		 */
		protected TextField txt_name;
		/**
		 * Spinner fuer die Menge der Zutat
		 */
		protected Spinner<Integer> spn_menge;
		/**
		 * Textfeld fuer die Einheit der Zutat
		 */
		protected TextField txt_einheit;
		/**
		 * Button zum entfernen der Zutat
		 */
		protected Button btn_entfernen;		

		/**
		 * Generiert eine neue ZutatenBearbeitung anhand der Werte einer Zutat
		 * @param zutat die zu bearbeitende Zutat
		 * @param controller Instanz der Steuerungsklasse
		 */
		ZutatenBearbeitung(Zutat zutat,  BearbeitenAnsichtSteuerung controller) {
			txt_name = new TextField(zutat.getName());
			txt_einheit = new TextField(zutat.getEinheit());
			spn_menge = new Spinner<Integer>(0, Integer.MAX_VALUE, zutat.getMenge(), 10);
			btn_entfernen = new Button("-");
			
			spn_menge.setEditable(true);
			
			btn_entfernen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					controller.entferneZutat(zutat);
				}
			});
			
			getChildren().addAll(
				txt_name, spn_menge, txt_einheit, btn_entfernen
			);
		}
		
		/**
		 * erzeugt eine neue Zutat anhand der Werte in der GUI
		 * @return die Zutat, die durch die GUI beschrieben wird
		 */
		public Zutat getZutat() {
			return new Zutat(
				txt_name.getText(),
				spn_menge.getValue(),
				txt_einheit.getText()
			);
		}
	}
	/**
	 * Innere Klasse zum Darstellen einer Bearbeitungsbox fuer Anweisungen
	 * @author Lars Hagemann
	 */
	protected class AnweisungenBearbeitung extends HBox {
		/**
		 * Label fuer die Nummer der Anweisung
		 */
		protected Label lbl_nummer;
		/**
		 * Textfeld fuer die Eingabe der Anweisung
		 */
		protected TextArea txt_input;
		/**
		 * Button zum loeschen der Anweisung
		 */
		protected Button btn_entfernen;
		
		/**
		 * Initialisiert eine Anweisungs Bearbeitungs Funktion
		 * @param anweisungsNummer die Nummer der Anweisung
		 * @param anweisung die Beschreibung der Anweisung
		 * @param controller Instanz der Steuerungsklasse
		 */
		public AnweisungenBearbeitung(int anweisungsNummer, String anweisung, BearbeitenAnsichtSteuerung controller) {
			lbl_nummer = new Label(anweisungsNummer + ". ");
			txt_input = new TextArea(anweisung);
			btn_entfernen = new Button("-");
			
			btn_entfernen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					controller.entferneAnweisung(anweisungsNummer);
				}
			});
			
			getChildren().addAll(lbl_nummer, txt_input, btn_entfernen);
		}
		
		/**
		 * @return Gibt den Inhalt dieser Bearbeitungsbox als Anweisung (String) zurueck
		 */
		public String getAnweisung() {
			return txt_input.getText();
		}
		
	}
	/**
	 * Label fuer den Rezepttitel
	 */
	protected Label lbl_name;
	/**
	 * Textfeld zum speichern des Rezepttitels 
	 */
	protected TextField txt_name;
	/**
	 * Button zum Loeschen des Rezeptes
	 */
	protected Button btn_entferneRezept;
	/**
	 * Label fuer die Zutatenliste
	 */
	protected Label lbl_zutaten;
	/**
	 * Widgets zum Bearbeiten der Zutaten
	 */
	protected List<ZutatenBearbeitung> lst_zutaten;
	/**
	 * Button zum Hinzufuegen einer Zutat
	 */
	protected Button btn_neueZutat;
	/**
	 * Label fuer die Anweisungen
	 */
	protected Label lbl_anweisungen;
	/**
	 * Widgets zum Bearbeiten der Anweisungen
	 */
	protected List<AnweisungenBearbeitung> lst_anweisungen;
	/**
	 * Button zum Hinzufuegen einer neuen Anweisung
	 */
	protected Button btn_neueAnweisung;
	/**
	 * Button zum Speichern der Aenderungen
	 */
	protected Button btn_speichern;
	/**
	 * Button zum Verwerfen der Aenderungen
	 */
	protected Button btn_abbrechen;
	/**
	 * Label fuer die Zubereitungszeit
	 */
	protected Label lbl_zeit;
	/**
	 * Spinner fuer die Zubereitungszeit 
	 */
	protected Spinner<Integer> spn_zeit;
	/**
	 * Container fuer zutaten bearbeitung
	 */
	protected VBox box_zutaten;
	/**
	 * Container fuer anweisungen bearbeitung
	 */
	protected VBox box_anweisungen;
	/**
	 * Das Rezept, das bearbeitet wird. 
	 */
	protected Rezept rezept;
	/**
	 * Instanz der Steuerungsklasse
	 */
	protected BearbeitenAnsichtSteuerung controller;
	
	/**
	 * 
	 * @return Der Name des Rezeptes, wie er momentan in der GUI erscheint
	 */
	public String getName() {
		return txt_name.getText();
	}
	
	/**
	 * 
	 * @return Die Zubereitungsdauer des Rezeptes, wie sie momentan in der GUI erscheint
	 */
	public int getDauer() {
		return spn_zeit.getValue();
	}
	
	/**
	 * 
	 * @return Die Liste der Zutaten, wie sie momentan in der GUI erscheint.
	 */
	public List<Zutat> getZutaten() {
		var zutaten = new ArrayList<Zutat>();
		for (var child : box_zutaten.getChildren()) {
			if (child instanceof ZutatenBearbeitung) {
				zutaten.add(((ZutatenBearbeitung)child).getZutat());
			}
		}
		return zutaten;
	}
	
	/**
	 * 
	 * @return Die Liste der Anweisungen, wie sie momentan in der GUI erscheint.
	 */
	public List<String> getAnweisungen() {
		var zutaten = new ArrayList<String>();
		for (var child : box_anweisungen.getChildren()) {
			if (child instanceof AnweisungenBearbeitung) {
				zutaten.add(((AnweisungenBearbeitung)child).getAnweisung());
			}
		}
		return zutaten;
	}	
	
	/**
	 * Konstruktor fuer die Grenzklasse BearbeitenAnsicht
	 * @param rezept das Rezept, dass in dieser Ansicht bearbeitet werden soll
	 * @param controller die Steuerungsklasse fuer diese GUI
	 */
	public BearbeitenAnsicht(Rezept rezept, BearbeitenAnsichtSteuerung controller) {
		this.rezept = rezept;
		this.controller = controller;
		
		txt_name = new TextField();
		btn_speichern = new Button("Speichern");
		btn_abbrechen = new Button("Abbrechen");
		btn_neueZutat = new Button("+");
		btn_neueAnweisung = new Button("+");
		spn_zeit = new Spinner<Integer>(0, Integer.MAX_VALUE, rezept.getDauer(), 5);
		lbl_name = new Label("Name: ");
		lbl_zutaten = new Label("Zutaten: ");
		lbl_anweisungen = new Label("Anweisungen: ");
		lbl_zeit = new Label("Zubereitungszeit: ");
		box_zutaten = new VBox();
		box_anweisungen = new VBox();
		var pane = new BorderPane();
		
		btn_neueZutat.setText("+");
		
		txt_name.setText(rezept.getName());
		txt_name.setTooltip(new Tooltip("Name des Rezeptes"));
		
		var container = new VBox();
		var zeitBox = new HBox();
		
		zeitBox.getChildren().addAll(
			lbl_zeit, 
			spn_zeit
		);
		
		btn_neueZutat.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				rezept.zutaten = getZutaten();
				rezept.getZutaten().add(new Zutat("", 0, ""));
				ladeZutaten();
			}
		});
		btn_neueAnweisung.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				rezept.anweisungen = getAnweisungen();
				rezept.getAnweisungen().add("");
				ladeAnweisungen();
			}
		});
		
		ladeZutaten();
		ladeAnweisungen();
		
		container.getChildren().addAll(
			zeitBox, 
			box_zutaten, 
			box_anweisungen
		);
		
		btn_speichern.setTooltip(new Tooltip("Speichert oder erstellt ein neues Rezept."));
		btn_abbrechen.setTooltip(new Tooltip("Bricht die Bearbeitung ab."));
		
		btn_abbrechen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.handleAbbrechen();
			}
		});
		btn_speichern.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.handleSpeichern();
			}
		});
		
		var btns_bottom = new HBox();
		btns_bottom.getChildren().addAll(btn_speichern, btn_abbrechen);
		
		pane.setTop(txt_name);
		pane.setCenter(container);
		pane.setBottom(btns_bottom);
		pane.setPadding(new Insets(10, 10, 10, 10));
		setContent(pane);
	}
	
	/**
	 * Diese Funktion laedt die Zutaten des momentanen Rezeptes in die GUI
	 */
	protected void ladeZutaten() {
		box_zutaten.getChildren().clear();
		box_zutaten.getChildren().add(new Label("Zutaten:"));
		for (var zutat : rezept.getZutaten()) {
			var bearbeitung = new ZutatenBearbeitung(zutat, controller);
			box_zutaten.getChildren().add(bearbeitung);
		}
		
		box_zutaten.getChildren().add(btn_neueZutat);
	}
	
	/** 
	 * Diese Hilfsfunktion laedt die Anweisungen des momentanen Rezeptes in die GUI
	 */
	protected void ladeAnweisungen() {
		box_anweisungen.getChildren().clear();
		box_anweisungen.getChildren().add(new Label("Anweisungen: "));
		var i = 1;
		for (var anweisung : rezept.getAnweisungen()) {
			var bearbeitung = new AnweisungenBearbeitung(i, anweisung, controller);
			box_anweisungen.getChildren().add(bearbeitung);
			++i;
		}
		
		box_anweisungen.getChildren().add(btn_neueAnweisung);
	}
}
