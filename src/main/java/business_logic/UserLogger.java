package business_logic;

import dto.UserCrendentialsDTO;
import dto.temporal.SessionDTO;

public interface UserLogger {

	public SessionDTO logUser(UserCrendentialsDTO credentials);

	public void logout();
}
