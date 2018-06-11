package it.Daniele.gestore.calendario.model;

import java.time.ZonedDateTime;

public class Formula1Event extends Event {
	private static final long serialVersionUID = 1L;
	
	public Formula1Event(String title, ZonedDateTime start, ZonedDateTime finish, String description, String moreInfo) {
		super(title, start, finish, description, moreInfo);
		super.competitionType = CompetitionType.FORMULA1;
	}
	
	public Formula1Event(String title, ZonedDateTime start, ZonedDateTime finish, String description) {
		this(title, start, finish,description,null);
	}
}
