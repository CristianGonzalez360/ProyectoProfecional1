package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class NotInLowercaseValidator implements Validator<String> {
	
	private String message;
	
	public NotInLowercaseValidator(String message) {
		this.message = message;
	}
	
	@Override
	public List<String> validate(String t) {
		LinkedList<String> errors = new LinkedList<>();
		if(!t.equals(t.toLowerCase())) {
			errors.add(message);
		}
		return errors;
	}
}
