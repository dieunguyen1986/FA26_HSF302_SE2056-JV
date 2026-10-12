package fu.talenthub.candidate.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "candidate_skills")
@Data
public class CandidateSkills {

    @EmbeddedId
    private CandidateSkillId candidateSkillId;

    private LocalDate date;

}

