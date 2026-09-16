package candidate.services;

import candidate.entity.Candidate;
import candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("candidateService") // Duoc quan ly boi IoC Container - ApplicationContext
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    @Override
    public Candidate createCandidate(Candidate candidate) {
        // Validate
        System.out.println("createCandidate at Service: " + candidate);

        return null;
    }
}
