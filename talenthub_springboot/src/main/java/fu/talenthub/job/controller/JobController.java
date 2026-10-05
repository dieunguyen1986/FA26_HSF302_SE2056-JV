package fu.talenthub.job.controller;

import fu.talenthub.job.dto.CreateJobRequest;
import fu.talenthub.job.dto.JobResponse;
import fu.talenthub.job.services.DepartmentService;
import fu.talenthub.job.services.JobService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {
    private final JobService jobService;
    private final DepartmentService departmentService;

//    @PostMapping
//    public ResponseEntity<?> createJob(@Valid @ModelAttribute CreateJobRequest request, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(jobService.create(request));
//
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        return ResponseEntity.ok(jobService.findAll());
//    }
//
//    @GetMapping("/find")
//    public ResponseEntity<?> getById(@RequestParam("id") UUID id) {
//        return ResponseEntity.ok(jobService.findById(id));
//    }

    // 1. XEM (Read - List)
    @GetMapping
    public String listJobs(Model model) {

        JobResponse jobResponse2 = (JobResponse) model.asMap().get("jobRes2");
        JobResponse jobResponse = (JobResponse) model.asMap().get("jobRes");
        log.info("Job Response= {}", jobResponse);
        log.info("Job Response= {}", jobResponse2);

        model.addAttribute("jobs", jobService.findAll());
        return "jobs/job-list";
    }

    // 2. THÊM MỚI (Create)
    // 2.1 Hiển thị form Thêm mới
    @GetMapping("/detail")
    public String showCreateForm(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("actionUrl", "/jobs/create");

        model.addAttribute("departments", departmentService.findAll());

        return "jobs/job-detail";
    }

    // 2.2 Xử lý Form Thêm mới (Submit POST)
    @PostMapping
    public String processCreate(@ModelAttribute CreateJobRequest request) {
        jobService.create(request);
        return "redirect:/jobs";
    }

    // 3. SỬA (Update)
    // 3.1 Hiển thị form Sửa (Lấy dữ liệu cũ)
    @GetMapping(value = {"/{id}", "/"})
    public String showUpdateForm(@PathVariable(required = false, name = "id") UUID id, Model model) {
        model.addAttribute("job", jobService.findById(id));
        model.addAttribute("isEdit", true);
        model.addAttribute("actionUrl", "/jobs/update");
        return "jobs/job-detail";
    }

    // 3.2 Xử lý Form Sửa (Submit POST)
    @PostMapping("/update")
    public String processUpdate(@RequestParam("id") UUID id, @ModelAttribute CreateJobRequest request) {
        jobService.update(id, request);
        return "redirect:/jobs";
    }

    // 4. XÓA (Delete)
    @GetMapping("/delete")
    public String deleteJob(@RequestParam("id") UUID id) {
        jobService.delete(id);
        return "redirect:/jobs";
    }

    // 5. Publish to user (can see/apply)

    @GetMapping("/publishion/{id}")
    public RedirectView publishJob(@PathVariable("id") UUID id, Model model,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession session) {
        // Call service

        log.info("Job id {} - {}", id, jobService.findById(id));

        redirectAttributes.addFlashAttribute("jobRes2", jobService.findById(id));
        model.addAttribute("jobRes", jobService.findById(id));

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/jobs");

        return redirectView;
    }
}
