package pe.org.yian.oauth.auth.server.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.org.yian.oauth.auth.server.data.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
	
	List<Organization> findByUsersUsername(String username);
	
	Organization findByCodeAndUsersUsername(String code, String username);

	@Query("select o from Organization o "
			+ "left join o.users u "
			+ "where (o.code like %:code% or o.name like %:name%) "
			+ "and u.username = :username "
			+ "and o.status = :status")
	List<Organization> findByCodeContainingOrNameContainingAndUsersUsernameAndStatus(@Param("code") String code, 
			@Param("name") String name, 
			@Param("username") String username, 
			@Param("status") String status);
}
