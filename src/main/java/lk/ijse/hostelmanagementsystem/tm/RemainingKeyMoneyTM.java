package lk.ijse.hostelmanagementsystem.tm;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemainingKeyMoneyTM {
    private String room;
    private String roomType;
    private String studentName;
    private double fullKeyMoney;
    private double paidAmount;


    private StudentRoomDTO studentRoom;

}
