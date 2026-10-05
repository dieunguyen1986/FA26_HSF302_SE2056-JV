package fu.talenthub.job.services;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;

import java.util.List;
import java.util.UUID;

public interface JobService {
    JobResponse create(CreateJobRequest request);
    List<JobResponse> findAll();

    JobResponse findById(UUID id);

    JobResponse update(UUID id, CreateJobRequest request);

    void delete(UUID id);
}
