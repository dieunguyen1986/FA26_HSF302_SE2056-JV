package fu.talenthub.job.services;

import fu.talenthub.job.dto.SkillDto;
import fu.talenthub.job.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements  SkillService {
    private final SkillRepository skillRepository;

    @Override
    @Transactional
    public Page<SkillDto> findAll(Integer pageIndex, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "skillName");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        return skillRepository.findAll(pageable).map((skill)->{
            SkillDto skillDto = new SkillDto();
            skillDto.setId(skill.getId());
            skillDto.setSkillName(skill.getSkillName());
            skillDto.setCategory(skill.getCategory());
            skillDto.setStatus(skill.getStatus());
            return skillDto;
        });
    }
}
