package it.Daniele.gestore.campionato.persister;

import java.util.SortedSet;

import it.Daniele.gestore.calendario.model.Calendar;

public class Championship {
	private Calendar calendar;
	private SortedSet<Athlete> allAthlets;
	
	public Championship(Calendar calendar, SortedSet<Athlete> allAthlets) {
		super();
		
		if(calendar == null || allAthlets == null) throw new IllegalArgumentException("Invalid championship params");
		
		this.calendar = calendar;
		this.allAthlets = allAthlets;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		if(calendar == null) throw new IllegalArgumentException("Invalid championship params");
		this.calendar = calendar;
	}

	public SortedSet<Athlete> getAllAthlets() {
		return this.allAthlets;
	}

	public void setAllAthlets(SortedSet<Athlete> allAthlets) {
		if(allAthlets == null) throw new IllegalArgumentException("Invalid championship params");
		this.allAthlets = allAthlets;
	}
	
	
}
