package cameltest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CamelConfigurationProperties.class)
public class CamelConfiguration {

    @Bean
    RoutesBuilder exceptionHandling() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .doTry()
                            .transform().constant(">>>>> DO TRY")
                            .to("stream:out")
                            .process(new Processor() {
                                    @Override
                                    public void process(Exchange exchange) throws Exception {
                                        throw new RuntimeException("Stop!");
                                            }
                                    }
                            )
                        .endDoTry()
                        .doCatch(Exception.class)
                            .transform().constant(">>>>> DO CATCH")
                            .to("stream:out")
                            .stop()
                        .doFinally()
                            .transform().constant(">>>>> DO FINALLY")
                            .to("stream:out")
                        .end()
                        .transform().constant(">>>>> CONTINUE")
                        .to("stream:out");
            }
        };
    }
}
