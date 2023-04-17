package lk.ijse.hostelmanagementsystem.tm;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTM {
    private String roomId;
    private String roomType;
    private Double keyMoney;

    private RoomDTO roomDTO;
    private RoomTypeDTO roomTypeDTO;
}
