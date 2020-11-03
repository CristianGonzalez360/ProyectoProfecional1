package dto.validators;

public class Patterns {
	public static final String EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String TEXT_FIELD = "^(\\S)(.){1,75}(\\S)$";
	public static final String POSITIVE_INTEGER = "[\\s]*[0-9]*[1-9]+";
	public static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss";
}
