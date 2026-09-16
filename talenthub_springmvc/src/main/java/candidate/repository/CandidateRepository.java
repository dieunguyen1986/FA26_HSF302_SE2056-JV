package candidate.repository;

import candidate.entity.Candidate;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
}
