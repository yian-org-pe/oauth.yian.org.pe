package pe.org.yian.oauth.auth.server.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pe.org.yian.oauth.auth.data.dto.OrganizationDto;
import pe.org.yian.oauth.auth.server.service.OrganizationService;

@RestController
@RequestMapping(value = "/api/organizations")
public class OrganizationController {
	private static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	private OrganizationService organizationService;

	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create (@RequestBody OrganizationDto organization, UriComponentsBuilder b) {
		organizationService.create(organization);
		URI located = b.path("/api/organizations/{id}").buildAndExpand(organization.getCode()).toUri();
		LOGGER.trace("located at: {}", located);
		return ResponseEntity.created(located).build();
	}
}
