package pe.org.yian.oauth.auth.server.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pe.org.yian.oauth.auth.data.dto.OrganizationDto;
import pe.org.yian.oauth.auth.server.data.entity.Organization;
import pe.org.yian.oauth.auth.server.data.entity.User;
import pe.org.yian.oauth.auth.server.data.repository.OrganizationRepository;
import pe.org.yian.oauth.auth.server.data.repository.UserRepository;
import pe.org.yian.oauth.auth.server.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	private OrganizationRepository organizationRepository;
	private UserRepository userRepository;

	@Autowired
	public OrganizationServiceImpl(OrganizationRepository organizationRepository, UserRepository userRepository) {
		super();
		this.organizationRepository = organizationRepository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public void create(OrganizationDto organization) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		LOGGER.trace("creating a new organization to {}", username);
		
		Organization org = new Organization(organization.getCode(), organization.getName());
		organizationRepository.save(org);
		
		User user = userRepository.findOne(username);
		user.addOrganization(org);
		userRepository.save(user);
	}
}
