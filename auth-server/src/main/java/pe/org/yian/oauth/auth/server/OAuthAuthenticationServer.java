package pe.org.yian.oauth.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import pe.org.yian.oauth.auth.server.config.OAuth2AuthorizationServerConfig;

@SpringBootApplication
public class OAuthAuthenticationServer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OAuthAuthenticationServer.class);
	}

	public static void main (String[] args) {
		SpringApplication.run(OAuthAuthenticationServer.class, args);
	}
}
