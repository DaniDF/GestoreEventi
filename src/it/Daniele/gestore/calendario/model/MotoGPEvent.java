package it.Daniele.gestore.calendario.model;

import java.time.ZonedDateTime;

public class MotoGPEvent extends Event {
	private static final long serialVersionUID = 1L;
	
	public MotoGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description, String moreInfo) {
		super(title, start, finish, description, moreInfo);
		super.competitionType = CompetitionType.MOTOGP;
	}
	
	public MotoGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description) {
		this(title, start, finish,description,null);
	}
}
