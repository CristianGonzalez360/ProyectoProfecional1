package dto.validators;

public class Patterns {

	public static final String EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	/*
	 * public static final String DNI = "\\d{8}[A-HJ-NP-TV-Z]";
	 */
	public static final String DNI = "\\d{8}";

	public static final String TEXT_FIELD = "^(\\S)(.){1,75}(\\S)$";

	public static final String ALPHANUMERIC = "^[\\w\\-\\s]+$";

	public static final String NON_NEGATIVE_INTEGER_FIELD = "[\\s]*[0-9]*[1-9]+";

	public static final String NUMERIC = "[0-9]*";

	public static final String NAME = "[a-zA-Z]*";
}
