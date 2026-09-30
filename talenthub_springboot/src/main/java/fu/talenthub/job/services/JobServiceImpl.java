package fu.talenthub.job.services;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;
import fu.talenthub.job.entity.Job;
import fu.talenthub.job.entity.JobStatus;
import fu.talenthub.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public JobResponse create(CreateJobRequest request) {
        // Business Rule: Validate

        if (request.salaryMax().compareTo(request.salaryMin()) < 0) {
            throw new IllegalArgumentException("Max < Min salary!");
        }


        return JobResponse.from(jobRepository.save(toEntity(request)));
    }


    private Job toEntity(CreateJobRequest request) {
        Job job = new Job();
        job.setTitle(request.title());
        job.setDescription(request.description());
        job.setLocation(request.location());
        job.setSalaryMax(request.salaryMax());
        job.setSalaryMin(request.salaryMin());
        job.setStatus(JobStatus.DRAFT);
        job.setUtmMedium(request.utmMedium());
        job.setUtmSource(request.utmSource());
        job.setDeadline(request.deadline());
        job.setCreatedAt(OffsetDateTime.now());
        return job;
    }
}
