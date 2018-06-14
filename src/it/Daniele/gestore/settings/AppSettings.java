package it.Daniele.gestore.settings;

import java.time.format.FormatStyle;
import java.util.Locale;

public class AppSettings {
	private FormatStyle formatStyleStandard;
	private String fileSeparator;
	private Locale standardLocaleRead;
	private Locale standardLocalePrint;
	
	public AppSettings() {
		this.formatStyleStandard = FormatStyle.LONG;
		this.fileSeparator = ";";
		this.standardLocaleRead = Locale.ITALY;
		this.standardLocalePrint = Locale.getDefault();
	}

	public FormatStyle getFormatStyleStandard() {
		return this.formatStyleStandard;
	}

	void setFormatStyleStandard(FormatStyle formatStyleStandard) {
		this.formatStyleStandard = formatStyleStandard;
	}

	public String getFileSeparator() {
		return this.fileSeparator;
	}

	void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public Locale getStandardLocalePrint() {
		return this.standardLocalePrint;
	}

	void setStandardLocalePrint(Locale standardLocalePrint) {
		this.standardLocalePrint = standardLocalePrint;
	}
	
	public Locale getStandardLocaleRead() {
		return this.standardLocaleRead;
	}

	void setStandardLocaleRead(Locale standardLocaleRead) {
		this.standardLocaleRead = standardLocaleRead;
	}
}
