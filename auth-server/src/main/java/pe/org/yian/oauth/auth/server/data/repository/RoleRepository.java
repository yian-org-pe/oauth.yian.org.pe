package pe.org.yian.oauth.auth.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.org.yian.oauth.auth.server.data.entity.Role;

import java.util.Set;

/**
 * Created by jaxkodex on 30/04/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findByNameIn (Set<String> names);
}
