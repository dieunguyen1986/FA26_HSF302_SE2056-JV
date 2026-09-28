package candidate.repository;

import candidate.entity.Candidate;

import java.util.List;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    List<Candidate> findAll();
}
