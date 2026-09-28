package candidate.controller;

import candidate.dto.CandidateRequest;
import candidate.entity.University;
import candidate.services.CandidateService;
import candidate.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final UniversityService universityService;

    @GetMapping("/")
    public String listCandidate(Model model) {

        var candidates = candidateService.getAllCandidates();

        System.out.println("SIZE = " + candidates.size());

        model.addAttribute("candidates", candidates);
        return "list_candidate";
    }

    @GetMapping("/candidates")
    public String hello(Model model) {
        System.out.println("Candidate Management");
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "create_candidate";
    }

    @PostMapping("/candidates")
    public String create(@ModelAttribute CandidateRequest request) {

        System.out.println("create");
        System.out.println("fullName: " + request.getFullName());
        System.out.println("email: " + request.getEmail());

        try {
            candidateService.createCandidate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/candidates";
    }
}