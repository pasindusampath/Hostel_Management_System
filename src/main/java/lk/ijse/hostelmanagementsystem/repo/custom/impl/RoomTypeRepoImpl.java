package lk.ijse.hostelmanagementsystem.repo.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomTypeRepo;
import org.hibernate.Session;

import java.util.List;

public class RoomTypeRepoImpl implements RoomTypeRepo {
    @Override
    public RoomType search(String s, Session session) throws Exception {
        return session.get(RoomType.class,s);
    }

    @Override
    public void delete(RoomType roomType, Session session) throws Exception {
        session.delete(roomType);
    }

    @Override
    public void update(RoomType roomType, Session session) throws Exception {
        session.update(roomType);
    }

    @Override
    public RoomType save(RoomType roomType, Session session) throws Exception {
        roomType.setId((String)session.save(roomType));
        return roomType;
    }

    @Override
    public List<RoomType> getAll(Session session) throws Exception {
        return session.createCriteria(RoomType.class).list();
    }
}
