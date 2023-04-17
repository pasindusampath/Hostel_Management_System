package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room implements SuperEntity {
    @Id
    private String id;
    private String availability;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomTypeId")
    @ToString.Exclude
    private RoomType roomType;

    @OneToMany(mappedBy = "room",targetEntity = StudentRoom.class)
    @ToString.Exclude
    private List<StudentRoom> studentRoomList;
}
