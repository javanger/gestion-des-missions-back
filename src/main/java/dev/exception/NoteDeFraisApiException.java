package dev.exception;

public class NoteDeFraisApiException extends Exception {

	public NoteDeFraisApiException() {
		super();
	}

	public NoteDeFraisApiException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoteDeFraisApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteDeFraisApiException(String message) {
		super(message);
	}

	public NoteDeFraisApiException(Throwable cause) {
		super(cause);
	}
	
}
