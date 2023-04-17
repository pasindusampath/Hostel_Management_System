package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomRepoImpl;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRoomRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.RepoFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.RepoTypes;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JoinServiceImpl implements JoinService {
    StudentRoomRepo studentRoomRepo = RepoFactory.getInstance().getRepo(RepoTypes.STUDENT_ROOM);
    RoomRepo roomRepo = RepoFactory.getInstance().getRepo(RepoTypes.ROOM);
    FactoryConfiguration factory = FactoryConfiguration.getInstance();
    Converter converter = Converter.getInstance();

    @Override
    public List<RemainingKeyMoneyTM> getRemainingKeyMoneyDetails() {
        Session session = factory.getSession();
        try {
            List<StudentRoom> all = studentRoomRepo.getRemainingKeyMoneyList(session);
            return converter.getRemainingKeyMoneyList(all);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
    @Override
    public HashMap<String,Integer> getAvailableRoomCount(){
        Session session = FactoryConfiguration.getInstance().getSession();
        HashMap<String, Integer> hm = new HashMap<>();
        try {
            List<Object[]> count = roomRepo.getCount(session);
            hm=Converter.getInstance().convertObjectArrayToStringIntegerHashMap(count);
            System.out.println(hm);

            //System.out.println(notAvailableRooms);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return hm;
    }
}
