package it.Daniele.gestore.campionato.persister;

public class Athlete {
	private String name;
	private String surname;
	
	public Athlete(String name, String surname) {
		super();
		
		if(name == null || surname == null) throw new IllegalArgumentException("Invalid athlete data");
		
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if(name == null) throw new IllegalArgumentException("Invalid athlete data");
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		if(surname == null) throw new IllegalArgumentException("Invalid athlete data");
		this.surname = surname;
	}
}
