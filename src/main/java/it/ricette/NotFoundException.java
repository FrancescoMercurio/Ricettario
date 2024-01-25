package it.ricette;

public class NotFoundException extends RuntimeException {
   
	private static final long serialVersionUID = -4287290003189370318L;

	public NotFoundException(String message) {
        super(message);
    }
}