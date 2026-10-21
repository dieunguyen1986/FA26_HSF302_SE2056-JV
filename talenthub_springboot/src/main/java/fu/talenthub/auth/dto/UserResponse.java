package fu.talenthub.auth.dto;

import fu.talenthub.auth.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private UUID departmentId;
    private Set<RoleResponse> roles;
    private String ssoProviderId;
    private UserStatus status;
}
