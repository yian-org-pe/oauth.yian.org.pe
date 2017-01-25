package pe.org.yian.oauth.auth.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.org.yian.oauth.auth.server.data.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
