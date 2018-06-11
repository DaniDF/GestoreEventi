package it.Daniele.gestore.calendario.persister;

import java.io.IOException;

public class BadFileFormatException extends IOException {
	private static final long serialVersionUID = 1L;

	public BadFileFormatException() {
		super();
	}

	public BadFileFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BadFileFormatException(String arg0) {
		super(arg0);
	}

	public BadFileFormatException(Throwable arg0) {
		super(arg0);
	}
}
