package pe.org.yian.oauth.auth.server.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import pe.org.yian.oauth.auth.server.util.exception.ApplicationConstraintException;
import pe.org.yian.oauth.auth.server.util.exception.ApplicationException;
import pe.org.yian.oauth.auth.server.util.exception.GeneralStatus;
import pe.org.yian.oauth.auth.server.util.exception.ApplicationException.AppExType;
import pe.org.yian.oauth.auth.server.util.exception.LogicException;

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
		
		Organization org = new Organization(organization.getCode(), organization.getName(), organization.getStatus());
		organizationRepository.save(org);
		
		User user = userRepository.findOne(username);
		user.addOrganization(org);
		userRepository.save(user);
	}

	@Override
	public List<OrganizationDto> getAll() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Organization> organizations = organizationRepository.findByUsersUsername(username);
		List<OrganizationDto> data = new ArrayList<>();
		
		for (Organization org : organizations) {
			OrganizationDto o = new OrganizationDto(org.getCode(), org.getName(), org.getStatus());
			data.add(o);
		}
		return data;
	}

	@Override
	public List<OrganizationDto> find(String query) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Organization> organizations = organizationRepository.findByCodeContainingOrNameContainingAndUsersUsernameAndStatus(query, query, username, GeneralStatus.ENABLED.getCode());
		List<OrganizationDto> data = new ArrayList<>();
		
		for (Organization org : organizations) {
			OrganizationDto o = new OrganizationDto(org.getCode(), org.getName(), org.getStatus());
			data.add(o);
		}
		return data;
	}

	@Override
	public void update(OrganizationDto organization) throws ApplicationException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Organization org = organizationRepository.findByCodeAndUsersUsername(organization.getCode(), username);
		if (org == null) {
			LogicException ex = new ApplicationConstraintException("Organization not found or not required access met");
			throw new ApplicationException(AppExType.NOT_FOUND, ex);
		}
		org.setName(organization.getName());
		organizationRepository.save(org);
	}

	@Override
	public void changeStatus(String organizationCode, GeneralStatus status) throws ApplicationException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Organization org = organizationRepository.findByCodeAndUsersUsername(organizationCode, username);

		if (org == null) {
			LogicException ex = new ApplicationConstraintException("Organization not found or not required access met");
			throw new ApplicationException(AppExType.NOT_FOUND, ex);
		}
		
		org.setStatus(status.getCode());
		organizationRepository.save(org);
	}
}
