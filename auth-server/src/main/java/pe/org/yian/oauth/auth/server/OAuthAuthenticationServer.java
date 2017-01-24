package pe.org.yian.oauth.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuthAuthenticationServer {

	public static void main (String[] args) {
		SpringApplication.run(OAuthAuthenticationServer.class, args);
	}
}
