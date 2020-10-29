package business_logic.filters;

public class UsersDetailsServiceImpl implements UsersDetailsService {

	private UserDao dao;

	public UsersDetailsServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean exists(User user) {
		return dao.findUser(user) != null;
	}

	@Override
	public String getRoles(User user) {
		return dao.findUser(user).getRole();
	}
}
