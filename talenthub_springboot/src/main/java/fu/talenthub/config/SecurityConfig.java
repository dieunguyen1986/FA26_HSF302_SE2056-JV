package fu.talenthub.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Value("${security.remember-me.key:change-this-key-in-production}")
    private String rememberMeKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF is enabled by default.
        // Disable it only for stateless APIs that use another CSRF strategy.
//        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                                    "/",
                                    "/auths/**",
                                    "/public/**",
                                    "/error/403",
                                    "/css/**",
                                    "/js/**",
                                    "/images/**",
                                    "/favicon.ico"
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/auths/login")
                            .loginProcessingUrl("/auths/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .successHandler((request, response, authentication) -> {
                                UserDetails userDetails =
                                        (UserDetails) authentication.getPrincipal();

                                log.info("User name {}", userDetails.getUsername());

                                Collection<? extends GrantedAuthority> grantedAuthorities =
                                        userDetails.getAuthorities();

                                List<String> roles = grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .toList();

                                log.info("Granted authorities {}", roles);

                                String targetUrl = roles.contains("ROLE_RECRUITER")
                                        ? "/jobs"
                                        : "/public";

                                response.sendRedirect(
                                        response.encodeRedirectURL(
                                                request.getContextPath() + targetUrl
                                        )
                                );
                            })
                            .failureHandler((request, response, exception) -> {
                                log.warn("Authentication failed: {}", exception.getMessage());

                                response.sendRedirect(
                                        response.encodeRedirectURL(
                                                request.getContextPath() + "/auths/login?error"
                                        )
                                );
                            });
                })
                .rememberMe(rememberMe -> rememberMe
                        .key(rememberMeKey)
                        .rememberMeParameter("rememberMe")
                        .tokenValiditySeconds(14 * 24 * 60 * 60)
                        .userDetailsService(userDetailsService)
                )
                .logout(logout -> logout
                        .logoutUrl("/auths/logout")
                        .logoutSuccessUrl("/auths/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                )
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint((request, response, authException) -> {
                        response.sendRedirect(
                                request.getContextPath() + "/auths/login?notLogged"
                        );
                    });

                    exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.sendRedirect(
                                request.getContextPath() + "/auths/login?accessDenied"
                        );
                    });
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider(userDetailsService);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
