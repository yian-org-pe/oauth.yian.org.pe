package pe.org.yian.oauth.auth.server.service;

import pe.org.yian.oauth.auth.data.dto.OrganizationDto;

public interface OrganizationService {

	/**
	 * Creates a new organization and asigns it to the current user
	 * @param organization to be registered
	 */
	public void create(OrganizationDto organization);
}
