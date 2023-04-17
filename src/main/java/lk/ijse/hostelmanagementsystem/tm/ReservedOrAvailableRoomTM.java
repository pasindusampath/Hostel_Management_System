package lk.ijse.hostelmanagementsystem.tm;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservedOrAvailableRoomTM {
    private String roomID;
    private String description;
    private String type;

    private StudentRoomDTO studentRoom;

}
