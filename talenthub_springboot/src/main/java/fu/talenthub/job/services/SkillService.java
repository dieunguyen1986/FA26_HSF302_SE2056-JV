package fu.talenthub.job.services;

import fu.talenthub.job.dto.SkillDto;
import org.springframework.data.domain.Page;

public interface SkillService {
    Page<SkillDto> findAll(Integer pageIndex, Integer pageSize);
}
