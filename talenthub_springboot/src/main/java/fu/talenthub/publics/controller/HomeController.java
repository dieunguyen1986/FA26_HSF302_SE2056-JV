package fu.talenthub.publics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/public"})
    public String index() {
        return "public-layouts/home";
    }
}
