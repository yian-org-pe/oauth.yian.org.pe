package pe.org.yian.oauth.auth.data.dto;

import java.util.List;

public class UserDto {
	private String username;
	private String email;
	private String name;
	private List<RoleDto> roles;
	private List<OrganizationDto> organizations;
	
	public UserDto () {}

	public UserDto(String username, String email, String name, List<RoleDto> roles) {
		super();
		this.username = username;
		this.email = email;
		this.name = name;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public List<OrganizationDto> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationDto> organizations) {
		this.organizations = organizations;
	}
}
