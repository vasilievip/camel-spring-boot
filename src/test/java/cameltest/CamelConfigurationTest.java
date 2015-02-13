package cameltest;

import org.apache.camel.CamelContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = {CamelConfigurationTest.class, CamelConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CamelConfigurationTest extends Assert {

    @Autowired
    CamelContext camelContext;
    @Autowired
    private CamelConfigurationProperties configurationProperties;

    @Autowired
    private Environment env;

    @Value("${name}")
    String name;


    @Test
    public void shouldInjectCamelContext() {
        assertNotNull(camelContext);
        assertNotNull(configurationProperties);
        assertNotNull(env);
        assertNotNull(name);
    }

    @Test
    public void shouldLoadRoute() {
        camelContext.createProducerTemplate().sendBody("direct:start", "Start");
        assertEquals(1, camelContext.getRoutes().size());
    }
}
