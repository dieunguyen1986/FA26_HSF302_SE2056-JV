package candidate.controller;

import candidate.entity.University;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unis")
public class UniversityController {

    @GetMapping
    public List<String> listUnis(){
        return List.of("FPTU", "HUST");
    }

    @PostMapping
    public String postUnis(@ModelAttribute University unis){

        System.out.println("post unis: "+ unis);
        return "unis"; // JSON
    }
}
