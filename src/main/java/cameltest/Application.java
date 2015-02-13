package cameltest;

import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({CamelAutoConfiguration.class, CamelConfiguration.class})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}