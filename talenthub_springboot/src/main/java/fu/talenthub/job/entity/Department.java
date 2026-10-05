package fu.talenthub.job.entity;

import fu.talenthub.shared.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", length = 50, nullable = false)
    private String departmentName;

    private String description;

    @OneToMany(mappedBy = "department")
    private List<Job> jobs = new ArrayList<>();

}
