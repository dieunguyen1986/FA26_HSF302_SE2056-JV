package fu.talenthub.job.repository;

import fu.talenthub.job.entity.Skill;
import fu.talenthub.job.entity.SkillStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByStatus(SkillStatus status);

}
