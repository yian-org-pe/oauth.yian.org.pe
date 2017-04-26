package pe.org.yian.oauth.auth.server.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pe.org.yian.oauth.auth.data.dto.OrganizationDto;
import pe.org.yian.oauth.auth.server.service.OrganizationService;
import pe.org.yian.oauth.auth.server.util.exception.ApplicationException;
import pe.org.yian.oauth.auth.server.util.exception.GeneralStatus;

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

	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<OrganizationDto>> getAll (@RequestParam(required=false) String query) {
		List<OrganizationDto> data = new ArrayList<>();
		if (query == null) {
			data = organizationService.getAll();
		} else {
			data = organizationService.find(query);
		}
		return ResponseEntity.ok(data);
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create (@RequestBody OrganizationDto organization, UriComponentsBuilder b) {
		organizationService.create(organization);
		URI located = b.path("/api/organizations/{id}").buildAndExpand(organization.getCode()).toUri();
		LOGGER.trace("created a new organization located at: {}", located);
		return ResponseEntity.created(located).build();
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "/{code}", method = RequestMethod.POST)
	public ResponseEntity<?> create (@RequestBody OrganizationDto organization, 
			@PathVariable String code) throws ApplicationException {
		organization.setCode(code);
		organizationService.update(organization);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "/{code}/enable", method = RequestMethod.POST)
	public ResponseEntity<?> enable (@PathVariable String code) throws ApplicationException {
		organizationService.changeStatus(code, GeneralStatus.ENABLED);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ADMIN')")
	@RequestMapping(value = "/{code}/disable", method = RequestMethod.POST)
	public ResponseEntity<?> disable (@PathVariable String code) throws ApplicationException {
		organizationService.changeStatus(code, GeneralStatus.DISABLED);
		return ResponseEntity.ok().build();
	}
}
