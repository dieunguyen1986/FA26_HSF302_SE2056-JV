package fu.talenthub.publics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class HomeController {
    @GetMapping
    public String index(){
        return "/public-layouts/home";
    }
}
