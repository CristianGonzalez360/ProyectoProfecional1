package business_logic.filters;

import business_logic.exceptions.ForbiddenException;

public class Logger implements Handler {

	private Handler handler;

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
		UsersDetailsService service = new UsersDetailsService() {

			@Override
			public boolean exists(User user) {
				return true;
			}

			@Override
			public String getRoles(User user) {
				return "admin";
			}
		};

		Authorizator authorizator = new Authorizator(service);
		authorizator.setNext(new Tracer());

		Authenticator authenticator = new Authenticator(service);
		authenticator.setNext(authorizator);

		this.setNext(authenticator);
		handler.auth(command);
	}
}
