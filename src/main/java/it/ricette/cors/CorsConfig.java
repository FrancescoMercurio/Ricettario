package it.ricette.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Permette l'invio dei cookie
        config.addAllowedOrigin("*"); // Consenti tutte le origini, è possibile specificare origini specifiche se necessario
        config.addAllowedHeader("*"); // Consenti tutti gli header
        config.addAllowedMethod("*"); // Consenti tutti i metodi HTTP (GET, POST, PUT, DELETE, ecc.)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
