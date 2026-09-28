package candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class CandidateRequest {
    private Long id;

    private String fullName;

    private String email;
    private  String password;

    private String phoneNumber;
    private String status;
    private UUID unisId;
}
