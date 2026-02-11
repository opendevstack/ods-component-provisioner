package org.opendevstack.component_provisioner.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class ApplicationPropertiesConfiguration {

    @Bean("awxServiceConfig")
    @ConfigurationProperties(prefix = "component-provisioner.awx.service")
    public AWXServiceProps awxServiceProps() {
        return AWXServiceProps.builder().build();
    }

    @Bean("componentCatalogServiceConfig")
    @ConfigurationProperties(prefix = "component-provisioner.component-catalog.service")
    public ComponentCatalogServiceProps componentCatalogServiceProps() {
        return ComponentCatalogServiceProps.builder().build();
    }

    @Builder // useful for unit testing
    @Data
    public static class AWXServiceProps {
        private String username;
        private String password;
        private URL baseRestUrl;
    }

    @Builder // useful for unit testing
    @Data
    public static class ComponentCatalogServiceProps {
        private URL baseRestUrl;
    }
}
