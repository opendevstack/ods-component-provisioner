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
import java.util.List;

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

    @Bean("catalogItemGroupsRestrictionConfig")
    @ConfigurationProperties(prefix = "catalog.user-action.groups-restriction")
    public CatalogItemUserActionGroupsRestrictionProps catalogItemGroupsRestrictionConfig() {
        return CatalogItemUserActionGroupsRestrictionProps.builder().build();
    }

    @Bean("projectsInfoServiceCacheConfig")
    @ConfigurationProperties(prefix = "component-provisioner.caching.projects-info-services-cache")
    public ProjectsInfoServicesCacheProps projectsInfoServicesCacheProps() {
        return ProjectsInfoServicesCacheProps.builder().build();
    }

    @Bean("componentCatalogCacheConfig")
    @ConfigurationProperties(prefix = "component-provisioner.caching.component-catalog-cache")
    public ComponentCatalogCacheProps componentCatalogCacheProps() {
        return ComponentCatalogCacheProps.builder().build();
    }

    @Bean("projectsInfoServiceConfig")
    @ConfigurationProperties(prefix = "component-provisioner.projects-info-service.service")
    public ExternalServiceProps projectsInfoServiceServiceProps() {
        return ExternalServiceProps.builder().build();
    }

    @Bean("azureAdTokenServiceProps")
    @ConfigurationProperties(prefix = "component-provisioner.azure.token.url")
    public AzureAdTokenServiceProps azureAdTokenServiceProps() {
        return AzureAdTokenServiceProps.builder().build();
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
    public static class ComponentCatalogCacheProps {
        public static final String CACHE_NAME = "component-catalog-cache";

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
    }

    @Builder // useful for unit testing
    @Data
    public static class AzureAdTokenServiceProps {
        private String tokenRestUrl;
    }

    @Builder // useful for unit testing
    @Data
    public static class ComponentProvisionerParametersProps {
        private String[] blacklist;
    }

    @Builder
    @Data
    public static class CatalogItemUserActionGroupsRestrictionProps {
        private List<String> prefix;
        private List<String> suffix;
    }
}
