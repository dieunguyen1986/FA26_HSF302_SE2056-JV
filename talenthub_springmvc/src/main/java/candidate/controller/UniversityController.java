package candidate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unis")
public class UniversityController {

    @GetMapping
    public List<String> listUnis(){
        return List.of("FPTU", "HUST");
    }
}
