package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class PositiveDouble implements Validator<String> {

	
	private String message;

	public PositiveDouble(String message) {
		this.message = message;
	}
	
	@Override
	public List<String> validate(String target) {
		LinkedList<String> ret = new LinkedList<String>();
		double valor = 0;
		try {
			valor = Double.parseDouble(target);
			if (valor <= 0) {
				ret.add(message);
			}
		} catch (NumberFormatException e) {
			ret.add(message);
		}
		return ret;
	}

}
