package candidate.controller;

import candidate.entity.Candidate;
import candidate.services.CandidateService;
import org.springframework.web.bind.annotation.*;

// Duc, Cao Van Hung, Dat (1.0), Luu Tien Dung (1.0)
@RestController
public class CandidateController {
    // DI
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping(path = "/candidates", method = RequestMethod.GET)
    public String listCandidate() {

        return "list_candidate";
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");

        return "index"; // /WEB-INF/index.jsp
    }


    /*
    @RequestParam(name = "fullName") String fullName,
                         @RequestParam(name = "email") String email,
                         @RequestParam(name="password") String password,
                         @RequestParam(name="status") String status,
                         @RequestParam(name = "phoneNumber") String phoneNumber, HttpServletRequest request
     */
    @PostMapping("/candidates")
    public String create(@ModelAttribute Candidate candidate) {

        // Logging to console
        System.out.println("create");
        System.out.println("fullName: " + candidate.getFullName());
        System.out.println("email: " + candidate.getEmail());

        // Call service
        candidateService.createCandidate(candidate);

        return "create"; // @Controller: view name - ten man hinh; @RestController: JSON
    }


}
