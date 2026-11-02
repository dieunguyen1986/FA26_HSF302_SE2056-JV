package fu.talenthub.auth.services;

import fu.talenthub.auth.entity.Role;
import fu.talenthub.auth.entity.User;
import fu.talenthub.auth.entity.UserStatus;
import fu.talenthub.auth.repository.RoleRepository;
import fu.talenthub.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);

        User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Account not found!"));

        log.info("UserDetailsServiceImpl loadUserByUsername id:{}", user.getId());

        List<Role> roles = roleRepository.findAll(user.getId());

        // Store context
        // RECRUITER, CANDIDATE, ADMIN

        List<GrantedAuthority> grantedAuthorities = roles.stream().map((role) -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());

            return simpleGrantedAuthority;
        }).collect(Collectors.toList());

        //


        boolean active = user.getStatus() == UserStatus.ACTIVE;
        boolean accountNotLocked = user.getStatus() != UserStatus.LOCKED;

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                active,
                true,
                true,
                accountNotLocked,
                grantedAuthorities
        );

        return userDetails;
    }
}
