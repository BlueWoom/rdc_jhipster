package it.dlvsystem.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, it.dlvsystem.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, it.dlvsystem.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, it.dlvsystem.domain.User.class.getName());
            createCache(cm, it.dlvsystem.domain.Authority.class.getName());
            createCache(cm, it.dlvsystem.domain.User.class.getName() + ".authorities");
            createCache(cm, it.dlvsystem.domain.Azienda.class.getName());
            createCache(cm, it.dlvsystem.domain.Azienda.class.getName() + ".offertas");
            createCache(cm, it.dlvsystem.domain.Candidato.class.getName());
            createCache(cm, it.dlvsystem.domain.Candidato.class.getName() + ".skillUtentes");
            createCache(cm, it.dlvsystem.domain.Candidato.class.getName() + ".cvs");
            createCache(cm, it.dlvsystem.domain.Esperienza.class.getName());
            createCache(cm, it.dlvsystem.domain.Esperienza.class.getName() + ".occupaziones");
            createCache(cm, it.dlvsystem.domain.GapAnalysis.class.getName());
            createCache(cm, it.dlvsystem.domain.Istruzione.class.getName());
            createCache(cm, it.dlvsystem.domain.Istruzione.class.getName() + ".offertas");
            createCache(cm, it.dlvsystem.domain.Istruzione.class.getName() + ".cvIstruziones");
            createCache(cm, it.dlvsystem.domain.Navigator.class.getName());
            createCache(cm, it.dlvsystem.domain.Navigator.class.getName() + ".candidatoes");
            createCache(cm, it.dlvsystem.domain.Login.class.getName());
            createCache(cm, it.dlvsystem.domain.Login.class.getName() + ".navigators");
            createCache(cm, it.dlvsystem.domain.Login.class.getName() + ".aziendas");
            createCache(cm, it.dlvsystem.domain.Login.class.getName() + ".candidatoes");
            createCache(cm, it.dlvsystem.domain.Occupazione.class.getName());
            createCache(cm, it.dlvsystem.domain.Occupazione.class.getName() + ".offertaOccupazioneRichiestas");
            createCache(cm, it.dlvsystem.domain.Occupazione.class.getName() + ".esperienzas");
            createCache(cm, it.dlvsystem.domain.Occupazione.class.getName() + ".offertas");
            createCache(cm, it.dlvsystem.domain.Occupazione.class.getName() + ".skills");
            createCache(cm, it.dlvsystem.domain.Offerta.class.getName());
            createCache(cm, it.dlvsystem.domain.Offerta.class.getName() + ".offertaSkills");
            createCache(cm, it.dlvsystem.domain.Offerta.class.getName() + ".offertaOccupazioneRichiestas");
            createCache(cm, it.dlvsystem.domain.Offerta.class.getName() + ".occupaziones");
            createCache(cm, it.dlvsystem.domain.OffertaSkill.class.getName());
            createCache(cm, it.dlvsystem.domain.Skill.class.getName());
            createCache(cm, it.dlvsystem.domain.Skill.class.getName() + ".skillUtentes");
            createCache(cm, it.dlvsystem.domain.Skill.class.getName() + ".offertaSkills");
            createCache(cm, it.dlvsystem.domain.Skill.class.getName() + ".occupaziones");
            createCache(cm, it.dlvsystem.domain.Cv.class.getName());
            createCache(cm, it.dlvsystem.domain.Cv.class.getName() + ".cvIstruziones");
            createCache(cm, it.dlvsystem.domain.Cv.class.getName() + ".esperienzas");
            createCache(cm, it.dlvsystem.domain.CvIstruzione.class.getName());
            createCache(cm, it.dlvsystem.domain.SkillUtente.class.getName());
            createCache(cm, it.dlvsystem.domain.OffertaOccupazioneRichiesta.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
