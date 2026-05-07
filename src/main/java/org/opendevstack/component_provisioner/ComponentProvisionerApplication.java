package org.opendevstack.component_provisioner;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class,
        exclude = {SpringDocDataRestConfiguration.class}
)
@ComponentScan(
        basePackages = {
                "org.opendevstack.component_provisioner.server.api",
                "org.opendevstack.component_provisioner.server.controllers",
                "org.opendevstack.component_provisioner.server.facade",
                "org.opendevstack.component_provisioner.server.security",
                "org.opendevstack.component_provisioner.config",
                "org.opendevstack.component_provisioner.server.services",
                "org.opendevstack.component_provisioner.server.mappers",
                "org.opendevstack.component_provisioner.client.awx.v2"
        },
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@EnableAspectJAutoProxy
@EnableCaching
@EnableScheduling
public class ComponentProvisionerApplication {

    public static void main(String[] args) {
		// Required to workaround parallel() stream issues
		// due to Spring DevTools classloader issues
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ComponentProvisionerApplication.class, args);
    }

    @Bean(name = "jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
