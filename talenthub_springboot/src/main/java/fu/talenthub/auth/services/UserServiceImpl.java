package fu.talenthub.auth.services;

import fu.talenthub.auth.entity.User;
import fu.talenthub.auth.entity.UserStatus;
import fu.talenthub.auth.mapper.UserMapper;
import fu.talenthub.auth.dto.RegisterRequest;
import fu.talenthub.auth.entity.Role;
import fu.talenthub.auth.repository.RoleRepository;
import fu.talenthub.auth.repository.UserRepository;
import fu.talenthub.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_ROLE_NAME = "CANDIDATE";

    private final UserRepository userRepository; // DI - Inject
    private final UserMapper userMapper; // Inject
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(1, "Email is existed!");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException(2, "Phone is existed!");
        }

        User user = userMapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        Role candidateRole = roleRepository.findByRoleName(DEFAULT_ROLE_NAME)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(DEFAULT_ROLE_NAME)
                        .description("Default role for registered users")
                        .build()));
        user.getRoles().add(candidateRole);

        userRepository.save(user);

        return true;
    }
}
