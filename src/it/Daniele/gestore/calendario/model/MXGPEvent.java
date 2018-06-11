package it.Daniele.gestore.calendario.model;

import java.time.ZonedDateTime;

public class MXGPEvent extends Event {
	private static final long serialVersionUID = 1L;
	
	public MXGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description, String moreInfo) {
		super(title, start, finish, description, moreInfo);
		super.competitionType = CompetitionType.MOTOGP;
	}
	
	public MXGPEvent(String title, ZonedDateTime start, ZonedDateTime finish, String description) {
		this(title, start, finish,description,null);
	}
}
