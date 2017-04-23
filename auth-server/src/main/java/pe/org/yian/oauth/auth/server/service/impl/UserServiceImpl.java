package pe.org.yian.oauth.auth.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.server.data.entity.User;
import pe.org.yian.oauth.auth.server.data.repository.UserRepository;
import pe.org.yian.oauth.auth.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto load(String username) {
		LOGGER.trace("cargando usuario");
		User user = userRepository.findOne(username);
		if (user == null) {
			LOGGER.trace("usuario no encontrado");
			return null;
		}
		LOGGER.trace("usuario cargado %s", user.getUsername());
		UserDto userDto = new UserDto(user.getUsername(), user.getEmail(), user.getName());
		return userDto;
	}

	@Override
	public UserDto create(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disable(String username) {
		// TODO Auto-generated method stub
		
	}
}
