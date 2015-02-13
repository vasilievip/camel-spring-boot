package cameltest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class CamelConfigurationProperties {

    @Value("${name}")
    String name = "mut be taken from config";

    @Override
    public String toString() {
        return "CamelConfigurationProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}

