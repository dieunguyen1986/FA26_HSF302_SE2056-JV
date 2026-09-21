package candidate.services;

import candidate.dto.CandidateRequest;
import candidate.dto.CandidateResponse;
import candidate.entity.Candidate;

public interface CandidateService {

    CandidateResponse createCandidate(CandidateRequest request);
}
