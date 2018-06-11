package it.Daniele.gestore.calendario.model;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Calendar {
	private String name;
	private SortedSet<Event> calendar;
	
	public Calendar(String name, SortedSet<Event> calendar) {
		super();
		
		if(name == null || calendar == null) throw new IllegalArgumentException("Invalid calendar data");
		
		this.name = name;
		this.calendar = calendar;
	}
	
	public Calendar(String name) {
		this(name,new TreeSet<>());
	}
	
	public Calendar() {
		this("Default name");
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if(name == null) throw new IllegalArgumentException("Invalid calendar data");
		this.name = name;
	}

	public SortedSet<Event> getCalendar() {
		return this.calendar;
	}

	public void setCalendar(List<Event> calendar) {
		if(calendar == null) throw new IllegalArgumentException("Invalid calendar data");
		this.calendar = calendar.parallelStream().sorted().collect(Collectors.toCollection(TreeSet::new));
	}
}
