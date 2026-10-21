package fu.talenthub.job.mapper;

import fu.talenthub.job.dto.SkillDto;
import fu.talenthub.job.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    // Proxy Design Pattern
    SkillDto toDto(Skill skill);
}
