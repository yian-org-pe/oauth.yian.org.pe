package pe.org.yian.oauth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import pe.org.yian.oauth.client.service.MeService;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MeService me = (MeService) context.getBean(MeService.class);
        System.out.println(me.me()); */
    }
}
