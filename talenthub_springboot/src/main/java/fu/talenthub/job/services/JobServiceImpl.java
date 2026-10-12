package fu.talenthub.job.services;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;
import fu.talenthub.job.entity.Job;
import fu.talenthub.job.entity.JobStatus;
import fu.talenthub.job.exception.JobInvalidException;
import fu.talenthub.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    @Transactional
    public JobResponse create(CreateJobRequest request) {
        // Business Rule: Validate

        if (request.salaryMax().compareTo(request.salaryMin()) < 0) {
            throw new JobInvalidException("Max < Min salary!");
        }


        return JobResponse.from(jobRepository.save(toEntity(request)));
    }

    @Override
    @Transactional
    public List<JobResponse> findAll() {
        List<Job> jobs = jobRepository.findAll();

        List<JobResponse> jobResponses = jobs.stream().map((job) -> {
            return JobResponse.from(job);
        }).toList();

        return jobResponses;
    }

    @Override
    @Transactional
    public JobResponse findById(UUID id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Job not found!");
        });
        return JobResponse.from(job);
    }

    @Override
    @Transactional
    public JobResponse update(UUID id, CreateJobRequest request) {
        Job job = jobRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Job not found!");
        });

        job.setTitle(request.title());
        job.setDescription(request.description());
        job.setLocation(request.location());
        job.setSalaryMax(request.salaryMax());
        job.setSalaryMin(request.salaryMin());
        job.setUtmMedium(request.utmMedium());
        job.setUtmSource(request.utmSource());
        job.setDeadline(request.deadline());
        job.setUpdatedAt(OffsetDateTime.now());

        return JobResponse.from(jobRepository.save(job));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!jobRepository.existsById(id)) {
            throw new IllegalArgumentException("Job not found!");
        }
        jobRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void publish(UUID id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Job not found!");
        });

        job.setStatus(JobStatus.PUBLISHED);

        jobRepository.save(job);
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