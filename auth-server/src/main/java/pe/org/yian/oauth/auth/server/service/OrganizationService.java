package pe.org.yian.oauth.auth.server.service;

import java.util.List;

import pe.org.yian.oauth.auth.data.dto.OrganizationDto;
import pe.org.yian.oauth.auth.server.util.exception.ApplicationConstraintException;
import pe.org.yian.oauth.auth.server.util.exception.ApplicationException;
import pe.org.yian.oauth.auth.server.util.exception.GeneralStatus;

public interface OrganizationService {
	
	/**
	 * List all organizations the current user has access to
	 * @return list of organizations
	 */
	List<OrganizationDto> getAll();
	
	/**
	 * Searches a organization by code and name
	 * @param query to search
	 * @return list of organizations that match the criteria
	 */
	List<OrganizationDto> find(String query);

	/**
	 * Creates a new organization and asigns it to the current user
	 * @param organization to be registered
	 */
	void create(OrganizationDto organization);
	
	/**
	 * updates data from a org
	 * @param organization to be updated
	 * @throws ApplicationConstraintException when the user can not change this data 
	 */
	void update(OrganizationDto organization) throws ApplicationException;
	
	/**
	 * Disables/enables a organization by changing it's status to D (Disabled) or E(Enabled)
	 * @param organizationCode to disable/enable
	 * @param status to set
	 * @throws ApplicationException when organization is not found or user has no access
	 */
	void changeStatus(String organizationCode, GeneralStatus status) throws ApplicationException;
}
