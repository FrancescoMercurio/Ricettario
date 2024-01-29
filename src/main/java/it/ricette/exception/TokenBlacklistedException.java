package it.ricette.exception;

public class TokenBlacklistedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenBlacklistedException(String message) {
        super(message);
    }
}
