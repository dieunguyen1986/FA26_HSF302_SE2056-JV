package fu.talenthub.job.entity;

import fu.talenthub.shared.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
@Data
public class Skill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    private String category;

    @Enumerated(EnumType.STRING)
    private SkillStatus status;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();
}
