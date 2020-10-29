package business_logic.filters;

import business_logic.exceptions.ForbiddenException;

public interface Handler {

	void setNext(Handler handler);

	Handler getNext();

	void auth(User command) throws ForbiddenException;
}
