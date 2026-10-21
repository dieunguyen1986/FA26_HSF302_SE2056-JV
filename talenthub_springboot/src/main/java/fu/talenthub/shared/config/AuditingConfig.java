package fu.talenthub.shared.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * Enables automatic created/updated date and user tracking for entities that
 * extend BaseEntity.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware",
        dateTimeProviderRef = "auditingDateTimeProvider")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
//            Authentication authentication =
//                    SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication == null
//                    || !authentication.isAuthenticated()
//                    || authentication instanceof AnonymousAuthenticationToken) {
                return Optional.of("system");
//            }

//            return Optional.of(authentication.getName());
        };
    }

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
