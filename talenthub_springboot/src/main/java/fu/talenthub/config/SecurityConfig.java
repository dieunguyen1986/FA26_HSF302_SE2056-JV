package fu.talenthub.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf((csrf)->{
//            csrf.disable();
//        });

        http.authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/auths/**", "/public/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin((form) -> {
                    form.loginPage("/auths/login")
                            .loginProcessingUrl("/auths/login")
                            .usernameParameter("email") // dieunt
                            .passwordParameter("password") // 12345
                            .successHandler((request, response, authentication) -> {
                                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                                log.info("User name {}", userDetails.getUsername());

                                Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();

                                List<String> roles = grantedAuthorities
                                        .stream().map((grantedAuthority -> grantedAuthority.getAuthority())).toList();
                                log.info("grantedAuthorities {}", roles);

                                if(roles.isEmpty()){
                                    response.sendRedirect(request.getContextPath() + "/public");
                                }

                                if (roles.contains("ROLE_RECRUITER")) {
                                    log.info("Recruiter role");
                                    response.sendRedirect(response.encodeRedirectURL("/jobs"));
                                }
                            })
                            .failureHandler((request, response, exception) -> {
                                log.info("failureHandler");

                                response.sendRedirect(response.encodeRedirectURL("/auths/login"));
                            })
                    ;

                })
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);

        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);

        return providerManager;
    }
}
