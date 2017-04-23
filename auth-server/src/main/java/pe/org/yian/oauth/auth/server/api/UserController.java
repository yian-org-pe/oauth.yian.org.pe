package pe.org.yian.oauth.auth.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.server.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PreAuthorize("#oauth2.hasScope('test')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public UserDto byUsername(@PathVariable String username) {
		LOGGER.trace("Cargando usuario {}", username);
		return userService.load(username);
	}
}
