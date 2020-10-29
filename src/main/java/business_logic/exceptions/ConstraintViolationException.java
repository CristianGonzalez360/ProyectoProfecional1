package business_logic.exceptions;

public class ConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 790637002347083823L;

	public ConstraintViolationException(String message) {
		super(message);
	}
}
