package business_logic.filters;

import java.util.Date;

import business_logic.exceptions.ForbiddenException;

public class Tracer implements Handler {

	public Tracer() {
	}

	@Override
	public void setNext(Handler handler) {
	}

	@Override
	public Handler getNext() {
		return null;
	}

	@Override
	public void auth(User command) throws ForbiddenException {
		System.out.println("El user command efectuó la operación en " + new Date().toString());
	}
}
