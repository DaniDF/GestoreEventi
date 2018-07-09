package it.Daniele.gestore.calendario.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.Daniele.gestore.calendario.model.Event;
import it.Daniele.gestore.calendario.model.EventStatus;
import it.Daniele.gestore.calendario.persister.BadFileFormatException;
import it.Daniele.gestore.calendario.persister.MyPersister;
import it.Daniele.gestore.calendario.persister.Persister;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyController implements Controller {
	private List<Persister> persisterList;
	
	public MyController(List<File> fileList) {
		if(fileList == null) throw new IllegalArgumentException("Invalid persister list");
		this.persisterList = this.getPersisterList(fileList);
	}
	
	@Override
	public SortedSet<Event> getAllEvents() {
		SortedSet<Event> result = new TreeSet<>();
		
		try {
			for(Persister x : this.persisterList) {
				result.addAll(x.load().getCalendar());
			}
		} catch (BadFileFormatException e) {
			Controller.myAlert(AlertType.ERROR, e.toString(), ButtonType.CLOSE);
		}
		
		return result;
	}

	@Override
	public SortedSet<Event> getFiltredEvents(Predicate<? super Event> filter) {
		if(filter == null) throw new IllegalArgumentException("Invalidi filter");
		
		return this.getAllEvents().parallelStream().filter(filter).collect(Collectors.toCollection(TreeSet::new));
	}
	
	@Override
	public List<Event> getNextEvent() {
		SortedSet<Event> nextEvent = this.getFiltredEvents(x -> !x.getStatus().equals(EventStatus.FINISHED));
		
		return nextEvent.parallelStream().limit(2).collect(Collectors.toList());
	}
	
	public List<Persister> getPersisterList(List<File> fileList) {
		if(fileList == null || fileList.size() == 0) return null;
		
		List<Persister> result = new ArrayList<>();
		
		for(File x : fileList) {
			try {
				result.add(new MyPersister(new FileReader(x)));
			} catch(BadFileFormatException | FileNotFoundException e) {
				Controller.myAlert(AlertType.ERROR, "Errore Apertura file:\n" + x + "\n" + e, ButtonType.CLOSE);
			}
		}
		
		return result;
	}
}
