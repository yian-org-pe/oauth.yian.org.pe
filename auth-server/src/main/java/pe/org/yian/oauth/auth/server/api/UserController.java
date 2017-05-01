package pe.org.yian.oauth.auth.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.data.dto.request.PasswordResetRequest;
import pe.org.yian.oauth.auth.server.service.UserService;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("#oauth2.hasScope('read') and hasRole('ADMIN')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public UserDto byUsername(@PathVariable String username) {
		LOGGER.trace("Cargando usuario {}", username);
		return userService.load(username);
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserDto> create(@RequestBody UserDto user, UriComponentsBuilder b) {
		userService.create(user);
		URI located = b.path("/api/users/{username}").buildAndExpand(user.getUsername()).toUri();
		return ResponseEntity.created(located).build();
	}


	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "/{username}/password", method = RequestMethod.PUT)
	public UserDto passwordReset(@RequestBody PasswordResetRequest passwordResetRequest,
								 @PathVariable String username) {
		userService.changePassword(username, passwordResetRequest.getNewPassword());
		return userService.load(username);
	}
}
