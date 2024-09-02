package com.example.animalshelter.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.animalshelter.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

        private final AuthTokenFilter authTokenFilter;

        public WebSecurityConfig(AuthTokenFilter authTokenFilter) {
                this.authTokenFilter = authTokenFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                // Configuración de CORS
                                .cors(cors -> cors
                                                .configurationSource(request -> {
                                                        var config = new org.springframework.web.cors.CorsConfiguration();
                                                        config.setAllowedOrigins(List.of("http://localhost:3000")); // Ajusta
                                                                                                                    // esto
                                                                                                                    // según
                                                                                                                    // sea
                                                                                                                    // necesario
                                                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
                                                                        "OPTIONS"));
                                                        config.setAllowedHeaders(List.of("*"));
                                                        config.setAllowCredentials(true);
                                                        return config;
                                                }))
                                // Configuración de CSRF
                                .csrf(csrf -> csrf.disable()) // Considera habilitar CSRF en producción
                                // Configuración de rutas de acceso
                                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                .requestMatchers("/api/auth/**").permitAll() // Permitir acceso sin
                                                                                             // autenticación a las
                                                                                             // rutas de autenticación
                                                .anyRequest().authenticated())
                                // Añadir filtro JWT
                                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                                .allowedOrigins("http://localhost:3000") // Ajusta esto al dominio de tu frontend
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                .allowedHeaders("*")
                                .allowCredentials(true);
        }
}
