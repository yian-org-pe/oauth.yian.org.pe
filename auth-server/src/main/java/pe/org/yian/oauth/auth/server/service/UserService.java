package pe.org.yian.oauth.auth.server.service;

import pe.org.yian.oauth.auth.data.dto.UserDto;

public interface UserService {

	UserDto load (String username);
	UserDto create (UserDto user);
	UserDto update (UserDto user);
	void disable (String username);
}
