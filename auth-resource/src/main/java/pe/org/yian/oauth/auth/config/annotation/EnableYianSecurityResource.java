package pe.org.yian.oauth.auth.config.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jaxkodex on 01/05/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({YianSecurityResource.class, MethodSecurityConfig.class})
public @interface EnableYianSecurityResource {
}
