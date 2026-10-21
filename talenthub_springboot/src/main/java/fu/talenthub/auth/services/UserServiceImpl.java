package fu.talenthub.auth.services;

import fu.talenthub.auth.entity.User;
import fu.talenthub.auth.entity.UserStatus;
import fu.talenthub.auth.mapper.UserMapper;
import fu.talenthub.auth.dto.RegisterRequest;
import fu.talenthub.auth.repository.UserRepository;
import fu.talenthub.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository; // DI - Inject
    private final UserMapper userMapper; // Inject

    @Override
    public boolean register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email is existed!");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException("Phone is existed!");
        }

        User user = userMapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        user.setPasswordHash(request.getPassword());

        userRepository.save(user);

        return true;
    }
}
