package it.Daniele.gestore.calendario.persister;

import it.Daniele.gestore.calendario.model.Calendar;

public interface Persister {
	public Calendar load() throws BadFileFormatException;
}
