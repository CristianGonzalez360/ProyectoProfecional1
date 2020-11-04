package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class NotSpacesValidator implements Validator<String> {

	private String message;

	public NotSpacesValidator(String message) {
		this.message = message;
	}

	@Override
	public List<String> validate(String target) {
		int size = target.length();
		int withoutSpaces = target.trim().length();
		LinkedList<String> lst = new LinkedList<>();
		if (size > withoutSpaces)
			lst.add(message);
		return lst;
	}
}
