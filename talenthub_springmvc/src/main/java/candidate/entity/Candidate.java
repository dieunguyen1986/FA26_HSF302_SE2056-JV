package candidate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "candidates",
        uniqueConstraints = {
            @UniqueConstraint(name = "UNX_CAN", columnNames ={"email", "phone_number"})})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;
    private  String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;

    @ManyToOne
    @JoinColumn(name = "unis_id", referencedColumnName = "id")
    private University university;
}
