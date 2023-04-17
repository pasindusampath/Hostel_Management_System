package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRoomRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.RepoFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.RepoTypes;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;

public class StudentRoomServiceImpl implements StudentRoomService {
    StudentRoomRepo studentRoomRepo = RepoFactory.getInstance().getRepo(RepoTypes.STUDENT_ROOM);;
    FactoryConfiguration factory = FactoryConfiguration.getInstance();
    Converter converter = Converter.getInstance();
    @Override
    public List<ReservedOrAvailableRoomTM> getReservedRoomDetails() {
        Session session = factory.getSession();
        try {
            List<StudentRoom> reservedRoomList = studentRoomRepo.getReservedRoomList(session);
            return converter.convertToReserveRoomTM(reservedRoomList);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

        return null;
    }

    @Override
    public boolean updateKeyMoney(StudentRoomDTO studentRoomDTO){
        StudentRoom studentRoom = converter.toOnlyStudentRoom(studentRoomDTO);
        Session session = factory.getSession();
        try {
            return studentRoomRepo.updateKeyMoney(session,studentRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String,Double> getMonthlyIncome(){
        Session session = factory.getSession();
        try {
            List<Object[]> income = studentRoomRepo.getIncome(session);
            HashMap<String, Double> stringDoubleHashMap =
                    converter.convertObjectArrayToStringDoubleHashMap(income);
            return stringDoubleHashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
