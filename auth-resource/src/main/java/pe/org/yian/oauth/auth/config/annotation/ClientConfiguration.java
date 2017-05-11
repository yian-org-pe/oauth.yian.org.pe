package pe.org.yian.oauth.auth.config.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by jaxkodex on 01/05/17.
 */
//@Configuration
//@EnableOAuth2Client
public class ClientConfiguration {
    @Value("${oauth.token.uri:http://localhost:8080/oauth/token}")
    private String accessTokenUri;
    @Value("${oauth.client.id:bac8881e-2541-44d0-af9c-2fcd4fb94167}")
    private String clientId;
    @Value("${oauth.resource.id:client}")
    private String id;

    /*
    @Bean
    public OAuth2RestTemplate xmRestTemplate(OAuth2ClientContext oauth2ClientContext) {
        System.out.println("Generando cliente :o");
        System.out.println(oauth2ClientContext);
        System.out.println(oauth2ClientContext.getAccessToken());
        return new OAuth2RestTemplate(xmOauth2RemoteResource(), oauth2ClientContext);
    }

    @Bean
    public ClientCredentialsResourceDetails xmOauth2RemoteResource() {
        ClientCredentialsResourceDetails resDetails = new ClientCredentialsResourceDetails();
        resDetails.setAccessTokenUri(accessTokenUri);
        resDetails.setClientId(clientId);
        resDetails.setId(id);
        return resDetails;
    }
    */
}
