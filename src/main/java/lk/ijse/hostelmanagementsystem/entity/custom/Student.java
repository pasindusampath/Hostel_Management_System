package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String contact;
    private String gmail;

    @ToString.Exclude
    @OneToMany(targetEntity = StudentRoom.class,mappedBy = "student")
    private List<StudentRoom> studentRoomList;
}
