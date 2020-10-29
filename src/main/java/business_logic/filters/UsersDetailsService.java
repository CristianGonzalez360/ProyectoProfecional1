package business_logic.filters;

public interface UsersDetailsService {

	boolean exists(User user);

	String getRoles(User user);
}
