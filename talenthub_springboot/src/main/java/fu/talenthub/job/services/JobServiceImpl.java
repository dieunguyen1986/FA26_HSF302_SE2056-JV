package fu.talenthub.job.services;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;
import fu.talenthub.job.entity.Job;
import fu.talenthub.job.entity.JobStatus;
import fu.talenthub.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

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

    @Override
    public List<JobResponse> findAll() {
        List<Job> jobs = jobRepository.findAll();

        List<JobResponse> jobResponses = jobs.stream().map((job) -> {
            return JobResponse.from(job);
        }).toList();

        return jobResponses;
    }

    @Override
    public JobResponse findById(UUID id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Job not found!");
        });
        return JobResponse.from(job);
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

class FuncImpl implements Function<Job, JobResponse> {

    @Override
    public JobResponse apply(Job job) {
        return null;
    }
}
