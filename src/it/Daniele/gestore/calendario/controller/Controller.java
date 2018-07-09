package it.Daniele.gestore.calendario.controller;

import java.util.List;
import java.util.SortedSet;
import java.util.function.Predicate;

import it.Daniele.gestore.calendario.model.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public interface Controller {
	public SortedSet<Event> getAllEvents();
	public SortedSet<Event> getFiltredEvents(Predicate<? super Event> verifier);
	public List<Event> getNextEvent();
	
	public static void myAlert(AlertType alertType,String msg, ButtonType buttonType) {
		try {
			Alert alert = new Alert(AlertType.ERROR, msg, ButtonType.OK);
			alert.showAndWait();
		} catch(IllegalStateException e) {
			if(System.console() != null) {
				System.err.println(msg);
			}
		}
	}
}
