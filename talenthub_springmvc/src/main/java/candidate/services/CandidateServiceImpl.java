package candidate.services;
// Phuowcs

import candidate.dto.CandidateRequest;
import candidate.dto.CandidateResponse;
import candidate.entity.Candidate;
import candidate.entity.CandidateStatus;
import candidate.entity.University;
import candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("candidateService") // Duoc quan ly boi IoC Container - ApplicationContext
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    @Override
    @Transactional
    public CandidateResponse createCandidate(CandidateRequest request) {
        // Validate
        System.out.println("createCandidate at Service: " + request.toString());

        return toResponse(candidateRepository.save(toEntity(request)));
    }

    private Candidate toEntity(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .id(request.getId())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .status(CandidateStatus.valueOf(request.getStatus()))
                .build();

//        University university = University.builder().id(request.getUnisId()).build();
//        candidate.setUniversity(university);

        return candidate;
    }

    private CandidateResponse toResponse(Candidate candidate) {
        CandidateResponse candidateResponse = CandidateResponse.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .fullName(candidate.getFullName())
                .phoneNumber(candidate.getPhoneNumber())
//                .unisId(candidate.getId())
                .build();

        return candidateResponse;
    }
}
