package org.opendevstack.component_provisioner.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class ApplicationPropertiesConfiguration {

    @Bean("securityProps")
    @ConfigurationProperties(prefix = "component-provisioner.security")
    public SecurityProps securityProps() {
        return SecurityProps.builder().build();
    }

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

    @Bean("odsApiServerServiceConfig")
    @ConfigurationProperties(prefix = "component-provisioner.ods-api-service.service")
    public OdsApiServerServiceProps odsApiServerServiceProps() {
        return OdsApiServerServiceProps.builder().build();
    }

    @Bean("componentProvisionerParametersConfig")
    @ConfigurationProperties(prefix = "component-provisioner.parameters")
    public ComponentProvisionerParametersProps componentProvisionerParametersProps() {
        return ComponentProvisionerParametersProps.builder().build();
    }

    @Bean("projectsInfoServiceCacheConfig")
    @ConfigurationProperties(prefix = "component-provisioner.caching.projects-info-services-cache")
    public ProjectsInfoServicesCacheProps projectsInfoServicesCacheProps() {
        return ProjectsInfoServicesCacheProps.builder().build();
    }

    @Bean("projectsInfoServiceConfig")
    @ConfigurationProperties(prefix = "component-provisioner.projects-info-service.service")
    public ExternalServiceProps projectsInfoServiceServiceProps() {
        return ExternalServiceProps.builder().build();
    }

    @Bean("azureAdTokenServiceProps")
    @ConfigurationProperties(prefix = "component-provisioner.azure.token")
    public AzureAdTokenServiceProps azureAdTokenServiceProps() {
        return AzureAdTokenServiceProps.builder().build();
    }

    @Builder // useful for unit testing
    @Data
    public static class SecurityProps {
        private String sharedSecret;
    }

    @Builder // useful for unit testing
    @Data
    public static class ProjectsInfoServicesCacheProps {
        public static final String CACHE_NAME = "projects-info-services-cache";

        @Builder.Default
        private boolean enabled = true;
        private DataSize maxSize;
        @DurationUnit(ChronoUnit.MINUTES) // default units, e.g. 5 -> 5m (minutes)
        private Duration evictionInterval;
    }

    @Builder // useful for unit testing
    @Data
    public static class ExternalServiceProps {
        private URL baseRestUrl;
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

    @Builder // useful for unit testing
    @Data
    public static class OdsApiServerServiceProps {
        private URL baseRestUrl;
        private String oid;
    }

    @Builder // useful for unit testing
    @Data
    public static class AzureAdTokenServiceProps {
        private String url;
    }

    @Builder // useful for unit testing
    @Data
    public static class ComponentProvisionerParametersProps {
        private String[] blacklist;
    }

}
