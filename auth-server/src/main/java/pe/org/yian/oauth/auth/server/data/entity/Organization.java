package pe.org.yian.oauth.auth.server.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization {
	@Id
	private String code;
	private String name;
	
	@OneToMany(mappedBy="organization")
	private List<Application> applications;
	
	@ManyToMany(mappedBy = "organizations")
	private List<User> users;

	public Organization() {
		super();
	}

	public Organization(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Organization(String code, String name, List<Application> applications) {
		super();
		this.code = code;
		this.name = name;
		this.applications = applications;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
