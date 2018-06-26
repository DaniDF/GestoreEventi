package it.Daniele.gestore.calendario.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MotoGPEvent extends Event {
	private static final long serialVersionUID = 1L;
	
	public MotoGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description, String moreInfo) {
		super(title, start, finish, description, moreInfo);
		super.competitionType = CompetitionType.MOTOGP;
	}
	
	public MotoGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description) {
		this(title, start, finish,description,null);
	}
	
	@Override
	public String print() {
		StringBuilder result = new StringBuilder();
		DateTimeFormatter dtF = DateTimeFormatter.ofLocalizedDateTime(super.appSettings.getFormatStyleStandard()).withLocale(super.appSettings.getStandardLocalePrint());
		
		result.append("Title: ");
		result.append(this.getTitle());
		result.append("\nStart:\t");
		result.append(dtF.format(this.getStart()));
		result.append("\nFinish:\t");
		result.append(dtF.format(this.getFinish()));
		result.append("\n");
		result.append(this.getStatus().toString());
		result.append("\n\n");
		
		result.append(this.getDescription());
		
		return result.toString();
	}
}
