package dto.validators;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NotDateBefore implements Validator<Date>{
	 
	private String message;
	
	public NotDateBefore(String message) {
		this.message = message;
	}
	
	@Override
	public List<String> validate(Date date) {
		assert date != null;
		List<String> ret = new LinkedList<>();
		if (date.before(new Date())) {
			ret.add(message);
		}
		return ret;
	}
}
