package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomTypeRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomTypeRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.RepoFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.RepoTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepo roomTypeRepo;
    private final Converter converter;
    private final FactoryConfiguration factory;

    public RoomTypeServiceImpl(){
        roomTypeRepo= RepoFactory.getInstance().getRepo(RepoTypes.ROOM_TYPE);;
        converter=Converter.getInstance();
        factory=FactoryConfiguration.getInstance();
    }

    @Override
    public RoomTypeDTO search(String s) {
        Session session = factory.getSession();
        try {
            RoomType search = roomTypeRepo.search(s, session);
            if(search!=null){
                return converter.toOnlyRoomTypeDTO(search);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(RoomTypeDTO roomTypeDTO) {
        return false;
    }

    @Override
    public boolean update(RoomTypeDTO roomTypeDTO) {
        return false;
    }

    @Override
    public RoomTypeDTO save(RoomTypeDTO roomTypeDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        RoomType roomType = converter.toOnlyRoomType(roomTypeDTO);
        try {
            RoomType save = roomTypeRepo.save(roomType, session);
            transaction.commit();
            return converter.toOnlyRoomTypeDTO(save);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RoomTypeDTO> getAll() {
        Session session = factory.getSession();
        List<RoomTypeDTO> list = new ArrayList<>();
        try {
            List<RoomType> all = roomTypeRepo.getAll(session);
            for (RoomType room : all){
                list.add(converter.toOnlyRoomTypeDTO(room));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }
}
