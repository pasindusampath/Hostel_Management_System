package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.SuperService;

public interface TransactionalService extends SuperService {
    boolean reserveRoom(StudentRoomDTO studentRoomDTO);
    boolean makeLeave(StudentRoomDTO studentRoomDTO);
}
