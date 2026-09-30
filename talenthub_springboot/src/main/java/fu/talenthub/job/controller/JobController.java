package fu.talenthub.job.controller;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.services.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping
    public ResponseEntity<?> createJob(@Valid @ModelAttribute CreateJobRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }

        return ResponseEntity.status(HttpStatus.OK).body(jobService.create(request));

    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<?> getById(@RequestParam("id") UUID id) {
        return ResponseEntity.ok(jobService.findById(id));
    }
}
