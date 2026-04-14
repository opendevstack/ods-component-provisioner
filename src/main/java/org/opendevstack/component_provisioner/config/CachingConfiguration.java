package org.opendevstack.component_provisioner.config;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.cache.Caching;
import java.util.Map;

import static org.ehcache.config.units.MemoryUnit.MB;
import static org.ehcache.event.EventType.EVICTED;
import static org.ehcache.event.EventType.EXPIRED;
import static org.ehcache.event.EventType.REMOVED;

// FIXME: So complex cache configuration, let's simplify it by using Spring Boot's auto-configuration for caching and just configure the cache properties in application.yaml. See https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-caching-provider-ehcache2
@Configuration
@Slf4j
public class CachingConfiguration implements CacheEventListener<Object, Object> {

    @Bean
    public CacheManager cacheManager(ApplicationPropertiesConfiguration.ProjectsInfoServicesCacheProps config) {
        if(!config.isEnabled()) {
            log.info("Projects Info Service cache is disabled");
            return new NoOpCacheManager();
        }

        var ehCaches = ehCachesConfig(config.getMaxSize().toMegabytes());
        var ehCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        var ehDefaultConfig = new DefaultConfiguration(ehCaches, ehCachingProvider.getDefaultClassLoader());

        var cacheManager = ehCachingProvider.getCacheManager(ehCachingProvider.getDefaultURI(), ehDefaultConfig);

        return new JCacheCacheManager(cacheManager);
    }

    private Map<String, CacheConfiguration<?, ?>> ehCachesConfig(long cacheSize) {
        var ehPoolsBuilder = ResourcePoolsBuilder
                .newResourcePoolsBuilder()
                .offheap(cacheSize, MB);

        var ehEventListenerConfig = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(this, EXPIRED, REMOVED, EVICTED)
                .unordered()
                .asynchronous();

        var ehCacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ehPoolsBuilder)
                .withService(ehEventListenerConfig)
                .build();

        return Map.of(
                ApplicationPropertiesConfiguration.ProjectsInfoServicesCacheProps.CACHE_NAME, ehCacheConfig,
                ApplicationPropertiesConfiguration.ComponentCatalogCacheProps.CACHE_NAME, ehCacheConfig
        );
    }

    @Scheduled(fixedRateString = "#{projectsInfoServiceCacheConfig.getEvictionInterval().toString()}")
    @CacheEvict(cacheNames = ApplicationPropertiesConfiguration.ProjectsInfoServicesCacheProps.CACHE_NAME, allEntries = true)
    public void emptyProjectsInfoServiceCache() {
        log.debug("Emptying Projects Info Service cache...");
    }

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.debug("Cache event: {} {} {} {} {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(),
                cacheEvent.getNewValue(), cacheEvent.getOldValue());
    }
}