package fu.talenthub.job.services;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;

public interface JobService {
    JobResponse create(CreateJobRequest request);
}
