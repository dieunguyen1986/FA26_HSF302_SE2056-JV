package fu.talenthub.auth.controller;

import fu.talenthub.auth.dto.LoginRequest;
import fu.talenthub.auth.dto.RegisterRequest;
import fu.talenthub.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auths")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String viewRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "auth/register";
    }

    @GetMapping("/login")
    public String viewLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") @Valid RegisterRequest request, BindingResult bindingResult, Model model) {
        log.info("Request {}", request);

        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", request);
            return "auth/register";
        }

        userService.register(request);

        return "redirect:/auths/login";
    }
}
