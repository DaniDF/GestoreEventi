package it.Daniele.gestore.calendario.model;

public enum EventStatus {
	TO_START, IN_PROGRESS, FINISHED;
	
	@Override
	public String toString() {
		return super.toString().replace("_", " ");
	}
}
