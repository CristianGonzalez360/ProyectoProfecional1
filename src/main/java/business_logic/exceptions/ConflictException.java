package business_logic.exceptions;

public class ConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8523897752867170093L;

	public ConflictException(String message) {
		super("Conflicto : " + message);
	}
}
