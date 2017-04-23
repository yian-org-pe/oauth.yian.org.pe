package pe.org.yian.oauth.auth.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import pe.org.yian.oauth.auth.server.data.repository.UserRepository;
import pe.org.yian.oauth.auth.server.data.entity.Role;
import pe.org.yian.oauth.auth.server.data.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Loading user info");
		User user = userRepository.findOne(username);
		
		if (user == null) {
			LOGGER.info("user not found :/");
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
	}

}
