package fu.talenthub.auth.services;

import fu.talenthub.auth.dto.UserResponse;
import fu.talenthub.auth.entity.User;
import fu.talenthub.auth.entity.UserStatus;
import fu.talenthub.auth.mapper.UserMapper;
import fu.talenthub.auth.dto.RegisterRequest;
import fu.talenthub.auth.entity.Role;
import fu.talenthub.auth.repository.RoleRepository;
import fu.talenthub.auth.repository.UserRepository;
import fu.talenthub.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collector;

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

    @Override
    public Page<UserResponse> findByPage(Integer index, Integer size) {

        PageRequest pageRequest = PageRequest.of(index, size);

        Page<User> userPage = userRepository.findAll(pageRequest);

        Page<UserResponse> userResponses = userPage.map((user)->{
            UserResponse userResponse = new UserResponse();
            userResponse.setFullName(user.getFullName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPhone(user.getPhone());

            return  userResponse;
        });

        return userResponses;
    }
}
