package pe.org.yian.oauth.auth.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by jaxkodex on 01/05/17.
 */
@Configuration
@EnableOAuth2Client
@ComponentScan(basePackages = { "pe.org.yian.oauth.auth.config.annotation",
        "pe.org.yian.oauth.auth.resource.api" })
public class ClientConfiguration {

    @Bean
    @Primary
    public OAuth2RestTemplate xmRestTemplate(ClientCredentialsResourceDetails xmOauth2RemoteResource) {
        return new OAuth2RestTemplate(xmOauth2RemoteResource);
    }

    @Bean
    public ClientCredentialsResourceDetails xmOauth2RemoteResource() {
        return new ClientCredentialsResourceDetails();
    }
}
