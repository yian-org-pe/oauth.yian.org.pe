package pe.org.yian.oauth.client;

import org.springframework.context.annotation.*;

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
