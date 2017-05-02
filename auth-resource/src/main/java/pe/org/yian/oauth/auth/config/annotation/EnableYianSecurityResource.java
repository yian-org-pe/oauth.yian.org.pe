package pe.org.yian.oauth.auth.config.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import pe.org.yian.oauth.client.App;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jaxkodex on 01/05/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({YianSecurityResource.class,
        MethodSecurityConfig.class,
        ClientConfiguration.class,
        App.class})
public @interface EnableYianSecurityResource {
}
