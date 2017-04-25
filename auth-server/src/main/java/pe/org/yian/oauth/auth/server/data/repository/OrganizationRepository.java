package pe.org.yian.oauth.auth.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.org.yian.oauth.auth.server.data.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
