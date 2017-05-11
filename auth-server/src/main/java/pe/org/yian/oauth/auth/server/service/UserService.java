package pe.org.yian.oauth.auth.server.service;

import pe.org.yian.oauth.auth.data.dto.UserDto;

public interface UserService {

	UserDto load (String username);

	/**
	 * Will create a new user on the system, it will assign the first
	 * organization in the object, if none exists, it will assign the
	 * first of the current logged user.
	 *
	 * It will add user role by default
	 *
	 * if the username already exists on the system, returns null
	 *
	 * @param user to be created
	 * @return new user
	 */
	void create (UserDto user);

	/**
	 * Changes the password to a new value
	 * @param username whom password will be changed
	 * @param newPassword to asign to the user
	 */
	void changePassword(String username, String newPassword);
	UserDto update (UserDto user);

	/**
	 * disables a user by username
	 * @param username to be disabled
	 */
	void disable (String username);
	
//	void signUp (UserDto user);
}
