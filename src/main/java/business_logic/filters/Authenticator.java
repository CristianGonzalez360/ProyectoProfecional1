package business_logic.filters;

import business_logic.exceptions.ForbiddenException;

public class Authenticator implements Handler {

	private Handler handler;

	private UsersDetailsService service;

	public Authenticator(UsersDetailsService service) {
		this.service = service;
	}

	@Override
	public void setNext(Handler handler) {
		this.handler = handler;
	}

	@Override
	public Handler getNext() {
		return this.handler;
	}

	@Override
	public void auth(User user) throws ForbiddenException {
		if (service.exists(user)) {
			System.out.println("The user has valid credentials.");
			getNext().auth(user);
		} else {
			throw new ForbiddenException("Forbidden operation.");
		}
	}
}
