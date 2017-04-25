package pe.org.yian.oauth.auth.server.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="application")
	private List<OauthClientDetails> clients;
	
	@ManyToOne
	@JoinColumn(name = "organization_code")
	private Organization organization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OauthClientDetails> getClients() {
		return clients;
	}

	public void setClients(List<OauthClientDetails> clients) {
		this.clients = clients;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
