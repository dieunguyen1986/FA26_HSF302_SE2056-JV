package fu.talenthub.job.dto;

import fu.talenthub.job.entity.SkillStatus;
import lombok.Data;

@Data
public class SkillDto {

    private Long id;

    private String skillName;

    private String category;

    private SkillStatus status;

}

