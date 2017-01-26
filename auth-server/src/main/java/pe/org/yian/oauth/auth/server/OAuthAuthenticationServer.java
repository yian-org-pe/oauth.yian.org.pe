package pe.org.yian.oauth.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import pe.org.yian.oauth.auth.server.config.OAuth2AuthorizationServerConfig;

@SpringBootApplication
public class OAuthAuthenticationServer {

	public static void main (String[] args) {
		SpringApplication.run(OAuthAuthenticationServer.class, args);
	}
}
