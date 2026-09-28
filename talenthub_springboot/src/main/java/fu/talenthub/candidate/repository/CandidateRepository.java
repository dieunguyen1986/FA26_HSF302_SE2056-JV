package fu.talenthub.candidate.repository;


import fu.talenthub.candidate.entity.Candidate;

import java.util.List;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    List<Candidate> findAll();
}
