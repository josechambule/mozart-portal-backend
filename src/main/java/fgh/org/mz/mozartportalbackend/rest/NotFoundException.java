package fgh.org.mz.mozartportalbackend.rest;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5963458018259387508L;

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}
	
}
