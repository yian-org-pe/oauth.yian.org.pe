package pe.org.yian.oauth.auth.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.yian.oauth.auth.data.dto.RoleDto;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.server.data.entity.Role;
import pe.org.yian.oauth.auth.server.data.entity.User;
import pe.org.yian.oauth.auth.server.data.repository.UserRepository;
import pe.org.yian.oauth.auth.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDto load(String username) {
		LOGGER.trace("cargando usuario {}", username);
		User user = userRepository.findOne(username);
		if (user == null) {
			LOGGER.trace("usuario no encontrado");
			return null;
		}
		LOGGER.trace("usuario cargado {}", user.getUsername());
		List<RoleDto> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			RoleDto dto = new RoleDto(role.getName(), role.getDescription());
			roles.add(dto);
		}
		UserDto userDto = new UserDto(user.getUsername(), user.getEmail(), user.getName(), roles);
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
