package candidate.controller;

import candidate.dto.CandidateRequest;
import candidate.services.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Duc, Cao Van Hung, Dat (1.0), Luu Tien Dung (1.0)
@Controller
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

    @GetMapping("/")
    public String hello() {
        System.out.println("Candiate Management");

        return "create_candidate"; // /WEB-INF/views/create_candidate.html
    }


    @PostMapping("/candidates")

    public String create(
//            @RequestParam(name = "fullName") String fullName,
//            @RequestParam(name = "email") String email,
//            @RequestParam(name = "password") String password,
//            @RequestParam(name = "status") String status,
//            @RequestParam(name = "phoneNumber") String phoneNumber, HttpServletRequest request
//    ) {
            @ModelAttribute CandidateRequest request) {

        // Logging to console
        System.out.println("create");
        System.out.println("fullName: " + request.getFullName());
        System.out.println("email: " + request.getEmail());

        // Call service
        try {
            candidateService.createCandidate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/candidates"; // @Controller: view name - ten man hinh; @RestController: JSON
    }


}
