package business_logic.filters;

import business_logic.exceptions.ForbiddenException;

public class Authorizator implements Handler {

	private Handler handler;

	private UsersDetailsService service;

	public Authorizator(UsersDetailsService service) {
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
	public void auth(User command) throws ForbiddenException {
		if (service.getRoles(command).equals("admin")) {
			System.out.println("El role es admin, autorizado");
			if (handler != null)
				this.handler.auth(command);
		} else {
			throw new ForbiddenException("Solo admin");
		}
	}
}
