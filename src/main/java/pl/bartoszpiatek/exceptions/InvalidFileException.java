package pl.bartoszpiatek.exceptions;

public class InvalidFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFileException() {
	}

	public InvalidFileException(String message) {
		super(message);
	}

	
}
