package it.Daniele.gestore.settings.model;

import java.io.File;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public final class AppSettings {
	private FormatStyle fileFormatStyleStandard;
	private String fileSeparator;
	private Locale standardLocaleRead;
	private Locale standardLocalePrint;
	private FormatStyle formatStyleStandardPrint;
	
	private FormatStyle formatStyleStandardBackwardFile;
	private String fileSeparatorBackwardFile;
	private Locale standardLocaleReadBackwardFile;
	private Locale standardLocalePrintBackwardFile;
	
	private List<File> prefFiles;
	
	private String filesRemoteSource;
	private String filesLocalSource;
	
	private List<File> credentialsFiles;
	
	public AppSettings() {
		this.fileFormatStyleStandard = FormatStyle.LONG;
		this.fileSeparator = ";";
		this.standardLocaleRead = Locale.ITALY;
		this.standardLocalePrint = Locale.getDefault();
		this.formatStyleStandardPrint = FormatStyle.FULL;
		
		this.formatStyleStandardBackwardFile = FormatStyle.SHORT;
		this.fileSeparatorBackwardFile = " ";
		this.standardLocaleReadBackwardFile = Locale.ITALY;
		this.standardLocalePrintBackwardFile = Locale.ITALY;
		
		this.prefFiles = new LinkedList<>();
		
		this.filesRemoteSource = "http://danybiker24.altervista.org/GestoreEventiData/";
		this.filesLocalSource = "data" + File.separator;
		
		this.prefFiles.add(new File(this.filesLocalSource + "Motogp.txt"));
		this.prefFiles.add(new File(this.filesLocalSource + "Mondiali.txt"));
		
		this.credentialsFiles = new ArrayList<>();
		this.credentialsFiles.add(new File("credentials.dat"));
	}

	public FormatStyle getFileFormatStyleStandard() {
		return this.fileFormatStyleStandard;
	}

	void setFileFormatStyleStandard(FormatStyle formatStyleStandard) {
		if(formatStyleStandard == null) throw new IllegalArgumentException("Invalid format style");
		this.fileFormatStyleStandard = formatStyleStandard;
	}
	
	public FormatStyle getFormatStyleStandardPrint() {
		return this.formatStyleStandardPrint;
	}
	
	public void setFormatStyleStandardPrint(FormatStyle formatStyleStandardPrint) {
		if(formatStyleStandardPrint == null) throw new IllegalArgumentException("Invalid format style");
		this.formatStyleStandardPrint = formatStyleStandardPrint;
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
		if(standardLocalePrint == null) throw new IllegalArgumentException("Invalid standard");
		this.standardLocalePrint = standardLocalePrint;
	}
	
	public Locale getStandardLocaleRead() {
		return this.standardLocaleRead;
	}

	void setStandardLocaleRead(Locale standardLocaleRead) {
		if(standardLocaleRead == null) throw new IllegalArgumentException("Invalid standard");
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

	public FormatStyle getFormatStyleStandardBackwardFile() {
		return this.formatStyleStandardBackwardFile;
	}

	void setFormatStyleStandardBackwardFile(FormatStyle formatStyleStandardBackwardFile) {
		if(formatStyleStandardBackwardFile == null) throw new IllegalArgumentException("Invalid format style");
		this.formatStyleStandardBackwardFile = formatStyleStandardBackwardFile;
	}

	public String getFileSeparatorBackwardFile() {
		return this.fileSeparatorBackwardFile;
	}

	void setFileSeparatorBackwardFile(String fileSeparatorBackwardFile) {
		if(fileSeparatorBackwardFile == null) throw new IllegalArgumentException("Invalid standard");
		this.fileSeparatorBackwardFile = fileSeparatorBackwardFile;
	}

	public Locale getStandardLocaleReadBackwardFile() {
		return this.standardLocaleReadBackwardFile;
	}

	void setStandardLocaleReadBackwardFile(Locale standardLocaleReadBackwardFile) {
		if(standardLocaleReadBackwardFile == null) throw new IllegalArgumentException("Invalid standard");
		this.standardLocaleReadBackwardFile = standardLocaleReadBackwardFile;
	}

	public Locale getStandardLocalePrintBackwardFile() {
		return this.standardLocalePrintBackwardFile;
	}

	void setStandardLocalePrintBackwardFile(Locale standardLocalePrintBackwardFile) {
		if(standardLocalePrintBackwardFile == null) throw new IllegalArgumentException("Invalid standard");
		this.standardLocalePrintBackwardFile = standardLocalePrintBackwardFile;
	}

	public List<File> getCredentialsFiles() {
		return credentialsFiles;
	}

}
