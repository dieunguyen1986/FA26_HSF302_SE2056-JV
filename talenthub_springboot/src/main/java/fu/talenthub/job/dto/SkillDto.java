package fu.talenthub.job.dto;

import fu.talenthub.job.entity.SkillStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SkillDto {

    private Long id;

    @NotBlank(message = "Skill name can not blank!")
    @Pattern(regexp = "^[A-Za-z]+\\s$", message = "Skill invalid format (only character)")
    private String skillName;

    @NotBlank
    private String category;

    private SkillStatus status;

}

