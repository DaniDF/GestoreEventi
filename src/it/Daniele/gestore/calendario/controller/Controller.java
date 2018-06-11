package it.Daniele.gestore.calendario.controller;

import java.util.Optional;
import java.util.SortedSet;
import java.util.function.Predicate;

import it.Daniele.gestore.calendario.model.Event;

public interface Controller {
	public SortedSet<Event> getAllEvents();
	public SortedSet<Event> getFiltredEvents(Predicate<? super Event> verifier);
	public Optional<Event> getNextEvent();
}
