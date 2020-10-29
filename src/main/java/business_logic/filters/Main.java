package business_logic.filters;

import business_logic.exceptions.ForbiddenException;

public class Main {

	public static void main(String[] args) {
		try {
			new Logger().auth(new User("nn", "admin"));
		} catch (ForbiddenException e) {
			System.out.println(e.getMessage());
		}
	}
}
