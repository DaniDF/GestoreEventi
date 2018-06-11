package application;

import java.time.Duration;

public class DurationFormatter {
	public static String durationFormatter(Duration d) {
		if(d == null) throw new IllegalArgumentException("Invalid duration");
		
		StringBuilder result = new StringBuilder();
		
		long days = d.getSeconds() / (3600 * 24);
		long hours = (d.getSeconds() - (days*24*3600)) / 3600;
		long mins = (d.getSeconds() - (days*24*3600) - (hours*3600)) / 60;
		long secs = d.getSeconds() - (days*24*3600) - (hours*3600) - (mins*60);
		
		if(days > 0) {result.append(days); result.append("D ");}
		if(hours > 0 || days > 0) {result.append(hours); result.append("H ");}
		if(mins > 0 || hours > 0 || days > 0) {result.append(mins); result.append("M ");}
		else if(secs > 0) {result.append(secs); result.append("S");}
		else result.append("0S");
		
		return result.toString();
	}
}
