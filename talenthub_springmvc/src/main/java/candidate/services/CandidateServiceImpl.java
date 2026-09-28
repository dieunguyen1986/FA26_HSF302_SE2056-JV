package candidate.services;
// Phuowcs

import candidate.dto.CandidateRequest;
import candidate.dto.CandidateResponse;
import candidate.entity.Candidate;
import candidate.entity.CandidateStatus;
import candidate.entity.University;
import candidate.repository.CandidateRepository;
import candidate.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("candidateService") // Duoc quan ly boi IoC Container - ApplicationContext
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public CandidateResponse createCandidate(CandidateRequest request) {
        // Validate
        System.out.println("createCandidate at Service: " + request.toString());
        System.out.println("unisId: " + request.getUnisId());

        return toResponse(candidateRepository.save(toEntity(request)));
    }

    private Candidate toEntity(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .id(request.getId())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();

        if (request.getStatus() != null) {
            candidate.setStatus(CandidateStatus.valueOf(request.getStatus()));
        } else {
            candidate.setStatus(CandidateStatus.APPLY);
        }

        if (request.getUnisId() != null) {
            University university = universityRepository.findAll()
                    .stream()
                    .filter(u -> u.getId().equals(request.getUnisId()))
                    .findFirst()
                    .orElse(null);
            candidate.setUniversity(university);
        }

        return candidate;
    }

    private CandidateResponse toResponse(Candidate candidate) {
        CandidateResponse candidateResponse = CandidateResponse.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .fullName(candidate.getFullName())
                .phoneNumber(candidate.getPhoneNumber())
                .status(candidate.getStatus())
                .university(candidate.getUniversity())
                .build();

        return candidateResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
