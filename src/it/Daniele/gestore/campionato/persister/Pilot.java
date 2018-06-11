package it.Daniele.gestore.campionato.persister;

public class Pilot extends Athlete {
	private String team;
	private int points;

	public Pilot(String name, String surname, String team, int points) {
		super(name, surname);
		
		if(team == null) throw new IllegalArgumentException("Invalid pilot data");
		
		this.team = team;
		this.points = points;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		if(team == null) throw new IllegalArgumentException("Invalid pilot data");
		this.team = team;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	

}
