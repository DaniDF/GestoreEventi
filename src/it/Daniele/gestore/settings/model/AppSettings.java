package it.Daniele.gestore.settings.model;

import java.io.File;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public final class AppSettings {
	private FormatStyle formatStyleStandard;
	private String fileSeparator;
	private Locale standardLocaleRead;
	private Locale standardLocalePrint;
	private List<File> prefFiles;
	
	private String filesRemoteSource;
	private String filesLocalSource;
	
	public AppSettings() {
		this.formatStyleStandard = FormatStyle.LONG;
		this.fileSeparator = ";";
		this.standardLocaleRead = Locale.ITALY;
		this.standardLocalePrint = Locale.getDefault();
		this.prefFiles = new LinkedList<>();
		
		this.filesRemoteSource = "http://192.168.0.106/";
		this.filesLocalSource = "data" + File.separatorChar;
		
		this.prefFiles.add(new File(this.filesLocalSource + "Motogp.txt"));
		this.prefFiles.add(new File(this.filesLocalSource + "Mondiali.txt"));
	}

	public FormatStyle getFormatStyleStandard() {
		return this.formatStyleStandard;
	}

	void setFormatStyleStandard(FormatStyle formatStyleStandard) {
		if(formatStyleStandard == null) throw new IllegalArgumentException("Invalid format style");
		this.formatStyleStandard = formatStyleStandard;
	}

	public String getFileSeparator() {
		return this.fileSeparator;
	}

	void setFileSeparator(String fileSeparator) {
		if(fileSeparator == null) throw new IllegalArgumentException("Invalid file separator");
		this.fileSeparator = fileSeparator;
	}

	public Locale getStandardLocalePrint() {
		return this.standardLocalePrint;
	}

	void setStandardLocalePrint(Locale standardLocalePrint) {
		if(standardLocalePrint == null) throw new IllegalArgumentException("Invalid sandard");
		this.standardLocalePrint = standardLocalePrint;
	}
	
	public Locale getStandardLocaleRead() {
		return this.standardLocaleRead;
	}

	void setStandardLocaleRead(Locale standardLocaleRead) {
		if(standardLocaleRead == null) throw new IllegalArgumentException("Invalid sandard");
		this.standardLocaleRead = standardLocaleRead;
	}
	
	public List<File> getPrefFiles() {
		return this.prefFiles;
	}
	
	public void setPrefFiles(List<File> prefFiles) {
		if(prefFiles == null) throw new IllegalArgumentException("Invalid file list");
		this.prefFiles = prefFiles;
	}

	public String getFilesRemoteSource() {
		return this.filesRemoteSource;
	}

	public String getFilesLocalSource() {
		return this.filesLocalSource;
	}
}
