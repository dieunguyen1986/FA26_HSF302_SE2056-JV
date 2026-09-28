package fu.talenthub.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @GetMapping
    public String application(){
        return "application/list_application";
    }
}
