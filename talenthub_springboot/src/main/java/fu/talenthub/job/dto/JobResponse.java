package fu.talenthub.job.dto;

import fu.talenthub.job.entity.Job;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record JobResponse(
        UUID id,
        String title,
        String description,
        String location,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        String status,
        String utmSource,
        String utmMedium,
        OffsetDateTime deadline,
        OffsetDateTime publishedAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        String createdBy,
        String updatedBy
) {

    public static JobResponse from(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getStatus().toString(),
                job.getUtmSource(),
                job.getUtmMedium(),
                job.getDeadline(),
                job.getPublishedAt(),
                job.getCreatedAt(),
                job.getUpdatedAt(),
                job.getCreatedBy(),
                job.getUpdatedBy()
        );
    }
}
