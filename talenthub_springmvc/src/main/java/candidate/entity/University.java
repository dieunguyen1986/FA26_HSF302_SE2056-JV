package candidate.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class University {
    public Long id;
    public  String unisName;
    public String address;
    public String unisPhone;

    public University(String address, Long id, String unisName, String unisPhone) {
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
}
