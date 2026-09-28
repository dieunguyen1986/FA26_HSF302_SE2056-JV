package fu.talenthub.candidate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "unis_name", unique = true, nullable = false)
    public  String unisName;
    public String address;
    @Column(name = "unis_phone")
    public String unisPhone;

    public University(String address, UUID id, String unisName, String unisPhone) {
        this.address = address;
        this.id = id;
        this.unisName = unisName;
        this.unisPhone = unisPhone;
    }

    @Override
    public String toString() {
        return "University{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", unisName='" + unisName + '\'' +
                ", unisPhone='" + unisPhone + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "university")
    private List<Candidate> candidates = new ArrayList<>();
}
