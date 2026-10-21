package fu.talenthub.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {

    @NotBlank(message = "Full name can't empty!")
    @Size(max = 255)
    private String fullName;

    @NotBlank(message = "Email can't empty!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is invalid (....)")
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Password is invalid (....)")
    private String password;

    @NotBlank(message = "Password can't empty!")
    private String confirmPassword;

    @Size(max = 30)
    private String phone;

    private UUID departmentId;
}
