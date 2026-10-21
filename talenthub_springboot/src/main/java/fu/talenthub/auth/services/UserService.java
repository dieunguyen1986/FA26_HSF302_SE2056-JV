package fu.talenthub.auth.services;

import fu.talenthub.auth.dto.RegisterRequest;

public interface UserService {
    boolean register(RegisterRequest register);

}
