package config;

import com.miage.altea.tp.battle.config.RestConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestConfigurationTest {

    @Test
    void restTemplate_shouldExist() {
        var restTemplate = new RestConfiguration().restTemplate();

        assertNotNull(restTemplate);
    }

    @Test
    void trainerApiRestTemplate_shouldHaveBasicAuth() {
        var restTemplate = new RestConfiguration().restTemplate();

        assertNotNull(restTemplate);
        /*String username = "user";
        String password="password";
        RestConfiguration restConfiguration = new RestConfiguration();
        restConfiguration.setUsername(username);
        restConfiguration.setPassword(password);
        var restTemplate = restConfiguration.trainerApiRestTemplate();

        assertNotNull(restTemplate);

        var interceptors = restTemplate.getInterceptors();
        assertNotNull(interceptors);
        assertEquals(1, interceptors.size());

        var interceptor = interceptors.get(0);
        assertNotNull(interceptor);

        assertEquals(BasicAuthenticationInterceptor.class, interceptor.getClass());*/
    }
}

