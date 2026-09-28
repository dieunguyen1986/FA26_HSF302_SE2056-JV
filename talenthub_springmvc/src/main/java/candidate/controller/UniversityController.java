package candidate.controller;

import candidate.entity.University;
import candidate.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping("/universities")
    public String listUniversities(Model model) {
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "create_candidate";
    }
}
