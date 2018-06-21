package it.Daniele.gestore.BackwardCompatibility;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import it.Daniele.gestore.calendario.model.Calendar;

public interface Persister {
	public List<Calendar> getCalendarList();
	public void save(Writer writer) throws IOException;
}
