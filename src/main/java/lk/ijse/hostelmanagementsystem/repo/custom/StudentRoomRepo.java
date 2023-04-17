package lk.ijse.hostelmanagementsystem.repo.custom;

import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.SuperRepo;
import org.hibernate.Session;

import java.util.List;

public interface StudentRoomRepo extends SuperRepo<StudentRoom,String> {
    List<StudentRoom> getRemainingKeyMoneyList(Session session) throws Exception;
    List<StudentRoom> getReservedRoomList(Session session) throws Exception;
    boolean updateKeyMoney(Session session,StudentRoom studentRoom) throws Exception;
    boolean updateToDate(Session session,StudentRoom studentRoom) throws Exception;
    List<Object[]> getIncome(Session session) throws Exception;

}
