package it.Daniele.gestore.calendario.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.Duration;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.io.Serializable;

public abstract class Event implements Serializable, Comparable<Event> {
	CompetitionType competitionType;
	private String title;
	private ZonedDateTime start;
	private ZonedDateTime finish;
	private String description;
	private Optional<String> moreInfo;
	private EventStatus status;

	private static final FormatStyle FORMATSYLE_STANDARD = FormatStyle.LONG;
	private static Map<String,Supplier<? extends Event>> knownEventType;
	
	private static final long serialVersionUID = 1L;
	
	protected Event(String title, ZonedDateTime start, ZonedDateTime finish, String description, String moreInfo) {
		super();
		
		if(title == null || start == null || finish == null || description == null) throw new IllegalArgumentException("Invalid data");
		if(start.isAfter(finish)) throw new IllegalArgumentException("Invalid start - finish");
		
		this.title = title;
		this.start = start;
		this.finish = finish;
		this.description = description;
		this.moreInfo = Optional.ofNullable(moreInfo);
		
		if(ZonedDateTime.now().isBefore(this.getStart())) this.status = EventStatus.TO_START;
		else if(ZonedDateTime.now().isAfter(this.getFinish())) this.status = EventStatus.FINISHED;
		else this.status = EventStatus.IN_PROGRESS;
	}
	
	public Event(String title, ZonedDateTime start, ZonedDateTime finish, String description) {
		this(title,start,finish,description,null);
	}
	
	public CompetitionType getCompetitionType() {
		return this.competitionType;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		if(title == null) throw new IllegalArgumentException("Invalid title");
		
		this.title = title;
	}

	public ZonedDateTime getStart() {
		return this.start;
	}
	
	public void setStart(ZonedDateTime start) {
		if(start == null) throw new IllegalArgumentException("Invalid start");
		this.start = start;
	}
	
	public ZonedDateTime getFinish() {
		return this.finish;
	}
	
	public void setFinish(ZonedDateTime finish) {
		if(finish == null) throw new IllegalArgumentException("Invalid finish");
		this.finish = finish;
	}
	
	public Duration getDuration() {
		return Duration.between(this.getStart(), this.getFinish());
	}
	
	public String getDescription() {	//gara, qualifica ecc.
		return this.description;
	}
	
	public void setDescription(String description) {
		if(description == null) throw new IllegalArgumentException("Invalid description");
		
		this.description = description;
	}
	
	public Optional<String> getMoreInfo() {	//Altre info
		return this.moreInfo;
	}
	
	public void setMoreInfo(String moreInfo) {
		if(moreInfo == null) throw new IllegalArgumentException("Invalid info");
		
		this.moreInfo = Optional.ofNullable(moreInfo);
	}
	
	public EventStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(EventStatus status) {
		if(status == null) throw new IllegalArgumentException("Invalid status");
		
		this.status = status;
	}
	
	public String print() {
		StringBuilder result = new StringBuilder();
		DateTimeFormatter dtF = DateTimeFormatter.ofLocalizedDateTime(FORMATSYLE_STANDARD).withLocale(Locale.getDefault());
		
		result.append("Title: ");
		result.append(this.getTitle());
		result.append("\nStart:\t");
		result.append(dtF.format(this.getStart()));
		result.append("\nFinish:\t");
		result.append(dtF.format(this.getFinish()));
		result.append("\n\n");
		
		result.append(this.getDescription());
		
		result.append("\n\n");
		
		if(this.getMoreInfo().isPresent()) result.append(this.getMoreInfo().get());
		
		return result.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.getTitle());
		result.append(": ");
		result.append(this.getDescription());
		
		return result.toString();
	}
	
	@Override
	public int compareTo(Event that) {
		Comparator<Event> cmp = Comparator.comparing(Event::getStart)
										  .thenComparing(Event::getFinish)
										  .thenComparing(Event::getTitle);
		
		return cmp.compare(this, that);
	}
	
	public static Event getCorrectEventByName(String name) {
		if(knownEventType == null) knownEventType = new TreeMap<>();
		ZonedDateTime now = ZonedDateTime.now();
		
		knownEventType.put("motogp", () -> new MotoGPEvent("",now,now,""));
		knownEventType.put("formula1", () -> new Formula1Event("",now,now,""));
		knownEventType.put("mxgp", () -> new MXGPEvent("",now,now,""));
		knownEventType.put("sbk", () -> new SBKEvent("",now,now,""));
		
		Supplier<? extends Event> result = null;
		
		result = knownEventType.get(name.toLowerCase());
		
		if(result == null) throw new IllegalArgumentException("Invalid event name");
		
		return result.get();
	}
}
