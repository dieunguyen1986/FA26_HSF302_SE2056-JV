package candidate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CandidateController {

    @RequestMapping(path = "/candidates", method = RequestMethod.GET)
    public String listCandidate(){

        return "list_candidate";
    }

    @GetMapping("/hello")
    public  String hello(){
        System.out.println("hello");

        return "index"; // /WEB-INF/index.jsp
    }

}
