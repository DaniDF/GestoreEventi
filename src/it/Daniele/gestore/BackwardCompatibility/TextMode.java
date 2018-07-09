package it.Daniele.gestore.BackwardCompatibility;

import it.Daniele.gestore.calendario.controller.Controller;
import it.Daniele.gestore.calendario.controller.MyController;
import it.Daniele.gestore.calendario.model.Event;
import it.Daniele.gestore.calendario.model.EventStatus;
import it.Daniele.gestore.settings.model.AppSettings;

public class TextMode {
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings();
		
		it.Daniele.gestore.remote.RemoteControl.main(null);
		
		Controller controller = new MyController(appSettings.getPrefFiles());
		
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
