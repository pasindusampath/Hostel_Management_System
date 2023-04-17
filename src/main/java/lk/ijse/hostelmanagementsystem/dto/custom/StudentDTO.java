package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO  implements SuperDTO {
    private String id;
    private String name;
    private String address;
    private String city;
    private String contact;
    private String gmail;
    @ToString.Exclude
    private List<StudentRoomDTO> studentRoomList;
}
