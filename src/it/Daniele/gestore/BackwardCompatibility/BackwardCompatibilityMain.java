package it.Daniele.gestore.BackwardCompatibility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.Daniele.gestore.calendario.model.Calendar;
import it.Daniele.gestore.calendario.persister.MyPersister;
import it.Daniele.gestore.settings.model.AppSettings;

public class BackwardCompatibilityMain {

	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings();
		List<File> souceFiles = new ArrayList<>();

		Arrays.stream(args).forEach(x -> souceFiles.add(new File(x)));
		appSettings.getPrefFiles().parallelStream().forEach(x -> souceFiles.add(x));
		
		List<Calendar> resultCalendar = new ArrayList<>();
		
		for(File x : souceFiles) {
			try {
				it.Daniele.gestore.calendario.persister.Persister persister = new MyPersister(new FileReader(x));
				
				resultCalendar.add(persister.load());
			}
			catch(IOException e) {
				System.err.println("Invalid file name\n");
				e.printStackTrace();
			}
		}
		
		Persister resultPersister = new MyBackwardPersister();
		resultCalendar.parallelStream().forEach(x -> resultPersister.getCalendarList().add(x));
		
		try {
			resultPersister.save(new FileWriter("generatedFile.txt"));
		}
		catch (IOException e) {
			System.err.println("Procedure save error\n");
			e.printStackTrace();
		}
	}

}
