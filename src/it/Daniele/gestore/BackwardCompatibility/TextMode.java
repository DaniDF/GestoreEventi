package it.Daniele.gestore.BackwardCompatibility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.Daniele.gestore.calendario.controller.Controller;
import it.Daniele.gestore.calendario.controller.MyController;
import it.Daniele.gestore.calendario.model.Event;
import it.Daniele.gestore.calendario.model.EventStatus;
import it.Daniele.gestore.settings.model.AppSettings;

public class TextMode {
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings();
		List<it.Daniele.gestore.calendario.persister.Persister> sourceFile = new ArrayList<>();
		
		for(File x : appSettings.getPrefFiles()) {
			try {
				sourceFile.add(new it.Daniele.gestore.calendario.persister.MyPersister(new FileReader(x)));
			} catch (IOException e) {
				System.err.println("Error: can't open file " + x + "\n" + e);
			}
		}
		
		Controller controller = new MyController(sourceFile);
		
		StringBuilder result = new StringBuilder();
		
		for(Event x : controller.getFiltredEvents(x -> !x.getStatus().equals(EventStatus.FINISHED))) {
			result.append("\t");
			result.append(x);
			result.append("\n");
		}
		
		result.append("\n");
		
		if(controller.getNextEvent().size() > 1) {
			result.append(controller.getNextEvent().get(0).print());
		}
		
		System.out.println(result);
	}
}
