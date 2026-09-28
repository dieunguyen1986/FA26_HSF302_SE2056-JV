package candidate.dto;

import candidate.entity.CandidateStatus;
import candidate.entity.University;
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
    private CandidateStatus status;

    private University university;
}
