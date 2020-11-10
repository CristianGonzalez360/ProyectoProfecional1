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
		int valor = 0;
		try {
			valor = Integer.parseInt(target);
			if (valor < 0) {
				ret.add(message);
			}
		} catch (NumberFormatException e) {
			ret.add(message);
		}
		return ret;
	}
}