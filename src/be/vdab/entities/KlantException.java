package be.vdab.entities;

public class KlantException extends Exception {

	private static final long serialVersionUID = 1L;

	public KlantException() {
		super();
	}

	public KlantException(String message) {
		super(message);
	}

	public KlantException(Throwable cause) {
		super(cause);
	}

	public KlantException(String message, Throwable cause) {
		super(message, cause);
	}
}
