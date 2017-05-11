package pe.org.yian.oauth.auth.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pe.org.yian.oauth.auth.server.data.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Verifies if a username is already registered
     * @param username to verify
     * @return count of existing users with that username
     */
    Long countByUsername(String username);

    /**
     * Verifies if a user is being edited by an admin of the same organization
     * @param username to be edited
     * @param adminUsername admin user that is trying to modify the user
     * @return the user if they belong to the same organization
     */
    User findByUsernameAndOrganizationsUsersUsername(String username, String adminUsername);

    /**
     * Updates the enabled user status
     * @param username to be modified
     * @param enabled status to be updated
     */
    @Modifying
    @Query("update User u set u.enabled = ?2 where u.username = ?1")
    void setEnabledStatus (String username, Boolean enabled);
}
