package fu.talenthub.candidate.services;


import fu.talenthub.candidate.dto.CandidateRequest;
import fu.talenthub.candidate.dto.CandidateResponse;

import java.util.List;

public interface CandidateService {

    CandidateResponse createCandidate(CandidateRequest request);
    List<CandidateResponse> getAllCandidates();
}
