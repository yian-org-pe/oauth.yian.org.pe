package pe.org.yian.oauth.auth.server.service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pe.org.yian.oauth.auth.data.dto.OrganizationDto;
import pe.org.yian.oauth.auth.data.dto.RoleDto;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.server.data.entity.Organization;
import pe.org.yian.oauth.auth.server.data.entity.Role;
import pe.org.yian.oauth.auth.server.data.entity.User;
import pe.org.yian.oauth.auth.server.data.repository.OrganizationRepository;
import pe.org.yian.oauth.auth.server.data.repository.RoleRepository;
import pe.org.yian.oauth.auth.server.data.repository.UserRepository;
import pe.org.yian.oauth.auth.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserRepository userRepository;
	private OrganizationRepository organizationRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
						   OrganizationRepository organizationRepository,
						   RoleRepository roleRepository,
						   PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
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

		List<OrganizationDto> organizations = new ArrayList<>();
		for (Organization org : user.getOrganizations()) {
			OrganizationDto o = new OrganizationDto(org.getCode(), org.getName(), org.getStatus());
			organizations.add(o);
		}
		UserDto userDto = new UserDto(user.getUsername(), user.getEmail(), user.getName(), roles);
		userDto.setOrganizations(organizations);
		return userDto;
	}

	@Override
	@Transactional
	public void create(UserDto user) {
		if (userExists(user.getUsername())) {
			// TODO throw excpetion
			return;
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userRepository.findOne(username);
		Organization o = null;
		if (user.getOrganizations() != null && !user.getOrganizations().isEmpty()) {
			o = organizationRepository
					.findByCodeAndUsersUsername(user.getOrganizations().get(0).getCode(), username);
		}

		if (o == null) {
			o = u.getOrganizations().get(0);
		}

		Set<String> roleNames = new HashSet<>(Arrays.asList(USER_ROLE));

		for (RoleDto r:user.getRoles()) {
			roleNames.add(r.getName());
		}

		List<Role> userRoles = new ArrayList<>(roleRepository.findByNameIn(roleNames));

		User newUser = new User(user.getUsername(), user.getEmail(), user.getName(),
				passwordEncoder.encode(user.getPassword()), userRoles, new ArrayList<>(Arrays.asList(o)));

		userRepository.save(newUser);
	}

	@Override
	public UserDto update(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disable(String username) {
		String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!canEditUserInfo(adminUsername, username)) {
			// TODO throw exception
			return;
		}
		userRepository.setEnabledStatus(username, false);
	}

	@Override
	public void changePassword(String username, String newPassword) {
		String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsernameAndOrganizationsUsersUsername(username, adminUsername);
		if (user == null) {
			// TODO throw exception
			return;
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	private boolean userExists (String username) {
		return userRepository.countByUsername(username) > 0;
	}

	private boolean canEditUserInfo (String adminUsername, String editingUsername) {
		// TODO: Change this method to check if user is admin :o
		User user = userRepository.findByUsernameAndOrganizationsUsersUsername(editingUsername, adminUsername);
		return user != null;
	}
}
