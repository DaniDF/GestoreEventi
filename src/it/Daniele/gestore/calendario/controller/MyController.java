package it.Daniele.gestore.calendario.controller;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.Daniele.gestore.calendario.model.Event;
import it.Daniele.gestore.calendario.persister.BadFileFormatException;
import it.Daniele.gestore.calendario.persister.Persister;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyController implements Controller {
	private List<Persister> persisterList;
	
	public MyController(List<Persister> persisterList) {
		this.persisterList = persisterList;
	}
	
	@Override
	public SortedSet<Event> getAllEvents() {
		SortedSet<Event> result = new TreeSet<>();
		
		try {
			for(Persister x : this.persisterList) {
				result.addAll(x.load().getCalendar());
			}
		} catch (BadFileFormatException e) {
			myAlert(AlertType.ERROR, e.toString(), ButtonType.CLOSE);
		}
		
		return result;
	}

	@Override
	public SortedSet<Event> getFiltredEvents(Predicate<? super Event> filter) {
		if(filter == null) throw new IllegalArgumentException("Invalidi filter");
		
		return this.getAllEvents().parallelStream().filter(filter).collect(Collectors.toCollection(TreeSet::new));
	}
	
	@Override
	public Optional<Event> getNextEvent() {
		SortedSet<Event> nextEvent = this.getFiltredEvents(x -> !x.getStart().isBefore(ZonedDateTime.now()));
		
		return nextEvent.size() == 0 ? Optional.empty() : Optional.ofNullable(nextEvent.first());
	}
	
	public static void myAlert(AlertType alertType,String msg, ButtonType buttonType) {
		Alert alrt = new Alert(AlertType.ERROR, msg, ButtonType.OK);
		alrt.showAndWait();
	}

}
