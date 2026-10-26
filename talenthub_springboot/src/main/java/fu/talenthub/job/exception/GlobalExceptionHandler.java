package fu.talenthub.job.exception;

import fu.talenthub.shared.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public RedirectView handleException(IllegalArgumentException e, RedirectAttributes attributes) {

        log.error("Handling Exception: {}", e.getMessage());
        attributes.addFlashAttribute("error", e.getMessage());

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/jobs");
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @ExceptionHandler(BusinessException.class)
    public String handle(BusinessException e, Model model, RedirectAttributes attributes) {
        log.error("Handling Phong Exception: {}", e.getMessage());

        attributes.addFlashAttribute("error", e.getMessage());

        if (e.getErrorCode() == 2) {
            return "redirect:/auths/register?phoneError";
        } else {
            return "redirect:/auths/register?emailError";
        }

    }


    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.error("Handling Exception: {}", e.getMessage());

        return "error";
    }
}
