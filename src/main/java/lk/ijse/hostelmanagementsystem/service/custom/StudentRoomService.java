package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;

import java.util.HashMap;
import java.util.List;

public interface StudentRoomService {
    List<ReservedOrAvailableRoomTM> getReservedRoomDetails();
    boolean updateKeyMoney(StudentRoomDTO studentRoomDTO);
    HashMap<String,Double> getMonthlyIncome();
}
