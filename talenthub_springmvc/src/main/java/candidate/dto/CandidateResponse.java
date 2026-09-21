package candidate.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponse {
    private Long id;

    private String fullName;

    private String email;
    private String phoneNumber;
    private Long unisId;
}
