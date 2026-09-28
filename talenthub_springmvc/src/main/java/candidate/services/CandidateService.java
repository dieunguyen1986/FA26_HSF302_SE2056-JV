package candidate.services;

import candidate.dto.CandidateRequest;
import candidate.dto.CandidateResponse;
import candidate.entity.Candidate;

import java.util.List;

public interface CandidateService {

    CandidateResponse createCandidate(CandidateRequest request);
    List<CandidateResponse> getAllCandidates();
}
