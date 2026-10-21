package fu.talenthub.job.controller;

import fu.talenthub.job.dto.SkillDto;
import fu.talenthub.job.services.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<?> getAllSkill(
            @RequestParam(name = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize
    ) {

        return ResponseEntity.ok(skillService.findAll(pageIndex, pageSize));
    }

    @PostMapping
    public String addSkill(@ModelAttribute @Valid SkillDto request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "skill_detail";
        }

        return "skill_detail";
    }
}
