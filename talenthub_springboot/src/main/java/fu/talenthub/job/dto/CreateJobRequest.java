package fu.talenthub.job.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CreateJobRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 500, message = "Title must not exceed 500 characters")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @Size(max = 500, message = "Location must not exceed 500 characters")
        String location,

        @DecimalMin(value = "0.0", message = "SalaryMin must be greater than or equal to 0")
        @Digits(integer = 13, fraction = 2)
        BigDecimal salaryMin,

        @DecimalMin(value = "0.0", message = "SalaryMax must be greater than or equal to 0")
        @Digits(integer = 13, fraction = 2)
        BigDecimal salaryMax,

        @Size(max = 150) String utmSource,
        @Size(max = 150) String utmMedium,

        @Future(message = "Deadline must be in the future")
        OffsetDateTime deadline,
        @NotNull(message = "DepartmentId is required")
        Long departmentId
) {

    @AssertTrue(message = "salaryMax must be greater than or equal to salaryMin")
    public boolean isSalaryRangeValid() {
        return salaryMin == null || salaryMax == null || salaryMax.compareTo(salaryMin) >= 0;
    }
}