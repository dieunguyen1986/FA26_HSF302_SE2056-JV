package fu.talenthub.auth.services;

import fu.talenthub.auth.dto.RegisterRequest;
import fu.talenthub.auth.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    boolean register(RegisterRequest register);

    Page<UserResponse> findByPage(Integer index, Integer size);

}
