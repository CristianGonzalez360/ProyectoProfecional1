package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class Number implements Validator<String> {

	private String message;

	public Number(String message) {
		this.message = message;
	}

	@Override
	public List<String> validate(String target) {
		LinkedList<String> ret = new LinkedList<String>();
		try {
			Integer.parseInt(target);
		} catch(NumberFormatException e) {
			ret.add(message);
		}
		return ret;
	}
}