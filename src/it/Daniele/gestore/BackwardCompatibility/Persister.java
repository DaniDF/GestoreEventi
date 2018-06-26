package it.Daniele.gestore.BackwardCompatibility;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import it.Daniele.gestore.calendario.model.Calendar;

public interface Persister {
	List<Calendar> getCalendarList();
	void save(Writer writer) throws IOException;
}
