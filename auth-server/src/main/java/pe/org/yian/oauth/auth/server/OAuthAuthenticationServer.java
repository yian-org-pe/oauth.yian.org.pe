package pe.org.yian.oauth.auth.server;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import pe.org.yian.oauth.auth.server.config.OAuth2AuthorizationServerConfig;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OAuthAuthenticationServer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OAuthAuthenticationServer.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OAuthAuthenticationServer.class, args);
	}

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				// .alternateTypeRules(
				// newRule(typeResolver.resolve(DeferredResult.class,
				// typeResolver.resolve(ResponseEntity.class,
				// WildcardType.class)),
				// typeResolver.resolve(WildcardType.class)))
				// .useDefaultResponseMessages(false)
				// .globalResponseMessage(RequestMethod.GET,
				// newArrayList(new ResponseMessageBuilder()
				// .code(500)
				// .message("500 message")
				// .responseModel(new ModelRef("Error"))
				// .build()))
				// .securitySchemes(newArrayList(apiKey()))
				// .securityContexts(newArrayList(securityContext()))
				.enableUrlTemplating(true)
		// .globalOperationParameters(
		// newArrayList(new ParameterBuilder()
		// .name("someGlobalParameter")
		// .description("Description of someGlobalParameter")
		// .modelRef(new ModelRef("string"))
		// .parameterType("query")
		// .required(true)
		// .build()))
		// .tags(new Tag("Pet Service", "All apis relating to pets"))
		// .additionalModels(typeResolver.resolve(AdditionalModel.class))
		;
	}
}
