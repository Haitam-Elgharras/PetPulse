package ma.petpulse.petpulsecore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorizeRequests -> {
                            try {
                                authorizeRequests
                                        .requestMatchers("/auth/**")
                                        .permitAll()
                                        /**
                                         * This is deliberate behavior because some users had private information on their error pages
                                         * and thus they needed them secured by default. To help with this, Spring Security 6 secures all
                                         * dispatcher types (including ERROR dispatch) not just REQUEST dispatches. If you want to allow ERROR
                                         * dispatches, you can update your authorization rules by dispatcher type.
                                         * we can add the following line to make the error dispatch unsecured
                                         */
                                        .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .sessionManagement(sessionManagement -> sessionManagement
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                        .authenticationProvider(authenticationProvider)
                                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                        .csrf(AbstractHttpConfigurer::disable);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );




        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}