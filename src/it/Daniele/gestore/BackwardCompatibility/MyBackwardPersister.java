package it.Daniele.gestore.BackwardCompatibility;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

import it.Daniele.gestore.calendario.model.Calendar;
import it.Daniele.gestore.calendario.model.Event;
import it.Daniele.gestore.settings.model.AppSettings;

public class MyBackwardPersister implements Persister {
	private List<Calendar> calendarList;
	
	private AppSettings appSetting;
	
	public MyBackwardPersister() {
		this.appSetting = new AppSettings();
		
		this.calendarList = new LinkedList<>();
	}
	
	@Override
	public void save(Writer writer) throws IOException {
		try(PrintWriter out = new PrintWriter(writer)){
			for(Calendar x : this.calendarList) {
				SortedSet<Event> eventSet = x.getCalendar();
				
				for(Event ev : eventSet) {
					StringBuilder resultLine = new StringBuilder();
					
					resultLine.append(this.backwardDateTimeFormatter(ev.getStart()));
					resultLine.append(this.appSetting.getFileSeparatorBackwardFile());
					
					resultLine.append(this.backwardDateTimeFormatter(ev.getFinish()));
					resultLine.append(this.appSetting.getFileSeparatorBackwardFile());
					
					resultLine.append(ev.getTitle() + ":");
					resultLine.append(ev.getDescription());
					resultLine.append(";");
					resultLine.append(this.appSetting.getFileSeparatorBackwardFile());
					
					resultLine.append((ev.getMoreInfo().isPresent())? ev.getMoreInfo().get() : "");
					
					out.println(resultLine.toString());
				}
			}
		}
		
	}

	@Override
	public List<Calendar> getCalendarList() {
		return this.calendarList;
	}

	private String backwardDateTimeFormatter(ZonedDateTime zdt) {
		DateTimeFormatter dtF = DateTimeFormatter.ofLocalizedDateTime(this.appSetting.getFormatStyleStandardBackwardFile(),
																	  this.appSetting.getFormatStyleStandardBackwardFile())
												 .withLocale(this.appSetting.getStandardLocalePrintBackwardFile());
		
		String result = dtF.format(zdt);
		
		result = result.replace(",", "");
		result = result.concat(":00");
		
		String resultStart = result.substring(0, 6);
		String resultYear = result.substring(6,8);
		String resultEnd = result.substring(8);
		
		return resultStart + "20".concat(resultYear) + resultEnd;
	}
}
