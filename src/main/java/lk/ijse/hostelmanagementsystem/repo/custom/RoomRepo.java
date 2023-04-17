package lk.ijse.hostelmanagementsystem.repo.custom;

import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.repo.SuperRepo;
import org.hibernate.Session;

import java.util.List;

public interface RoomRepo extends SuperRepo<Room,String> {
    List<Room> getAvailableRooms(RoomType roomType, Session session) throws Exception;
    boolean updateAvailability(Room room,Session session) throws Exception;
    List<Room> getAvailableRooms(Session session) throws Exception;
    List<Object[]> getCount(Session session) throws Exception;
}
