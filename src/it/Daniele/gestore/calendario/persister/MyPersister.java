package it.Daniele.gestore.calendario.persister;

import java.io.Reader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.StringTokenizer;

import it.Daniele.gestore.calendario.model.Calendar;
import it.Daniele.gestore.calendario.model.Event;

import java.io.BufferedReader;
import java.io.IOException;

public class MyPersister implements Persister {
	private static final String SEPARATOR = ";";
	private static final Locale LOCALE_STANDARD = Locale.ITALY;
	private static final FormatStyle FORMATSYLE_STANDARD = FormatStyle.LONG;
	
	private Reader reader;
	private Calendar calendar;
	
	public MyPersister(Reader reader) throws BadFileFormatException {
		if(reader == null) throw new IllegalArgumentException("Invalid source");
		this.reader = reader;
		
		calendar = new Calendar();
		
		try(BufferedReader in = new BufferedReader(this.reader)) {
			String row = null;
			
			while((row= in.readLine()) != null) {
				if(!row.equals("")) {
					StringTokenizer str = new StringTokenizer(row, SEPARATOR);	//File format: -->"Titolo; dataStart; timeStart; dataFinish; timeFinish; descrizione; moreInfo"<--
					
					if(!str.hasMoreElements()) throw new BadFileFormatException("Invalid file format: name event missing");
					String nameEvent= str.nextToken().trim();
					
					Event temp = null;
					
					try {temp = Event.getCorrectEventByName(nameEvent); temp.setTitle(nameEvent);}
					catch(IllegalArgumentException e) {throw new BadFileFormatException("Invalid file format: name event incorrect");}
					
					DateTimeFormatter dtF = DateTimeFormatter.ofLocalizedDateTime(FORMATSYLE_STANDARD,FORMATSYLE_STANDARD).withLocale(LOCALE_STANDARD);
	
					if(!str.hasMoreElements()) throw new BadFileFormatException("Invalid file format: start date time event missing");
					ZonedDateTime startDateTime = ZonedDateTime.parse(str.nextToken().trim(), dtF);
					
					temp.setStart(startDateTime);
					
					if(!str.hasMoreElements()) throw new BadFileFormatException("Invalid file format: finish date time event missing");
					ZonedDateTime finishDateTime = ZonedDateTime.parse(str.nextToken().trim(), dtF);
					
					temp.setFinish(finishDateTime);
					
					if(str.hasMoreElements()) {
						temp.setDescription(str.nextToken().trim());
						
						if(str.hasMoreElements()) {
							temp.setMoreInfo(str.nextToken().trim());
							
							if(str.hasMoreElements()) throw new BadFileFormatException("Invalid fileformat: too much para");
						}
					}
					
					calendar.getCalendar().add(temp);
				}
				
			}
		} catch (IOException e) {
			throw new BadFileFormatException("Can't open source", e);
		} catch (DateTimeParseException e) {
			throw new BadFileFormatException("Invalid file format: start or finish date or time event incorrect", e);
		}
	}

	@Override
	public Calendar load() throws BadFileFormatException {
		return this.calendar;
	}
	
}
