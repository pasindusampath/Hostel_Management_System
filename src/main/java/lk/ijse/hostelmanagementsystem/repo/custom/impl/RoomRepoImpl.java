package lk.ijse.hostelmanagementsystem.repo.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoomRepoImpl implements RoomRepo {
    @Override
    public Room search(String s, Session session) throws Exception {
        return session.get(Room.class,s);
    }

    @Override
    public void delete(Room room, Session session) throws Exception {
        session.delete(room);
    }

    @Override
    public void update(Room room, Session session) throws Exception {
        session.update(room);
    }

    @Override
    public Room save(Room room, Session session) throws Exception {
        room.setId((String)session.save(room));
        return room;
    }

    @Override
    public List<Room> getAll(Session session) throws Exception {
        return session.createCriteria(Room.class).list();
    }

    @Override
    public List<Room> getAvailableRooms(RoomType roomType,Session session) throws Exception{
        Query query = session.createQuery(" from Room r where r.availability = :av AND r.roomType.id = :typeId");
        query.setParameter("av","Available");
        query.setParameter("typeId",roomType.getId());
        List<Room> list = query.list();
        return list;
    }

    @Override
    public boolean updateAvailability(Room room,Session session) throws Exception{
        Query query = session.createQuery("update Room r set r.availability = :av where r.id = :id");
        query.setParameter("av",room.getAvailability());
        query.setParameter("id",room.getId());
        return query.executeUpdate()>0;
    }

    @Override
    public List<Room> getAvailableRooms(Session session) throws Exception{
        Query query = session.createQuery(" from Room r where r.availability = :av");
        query.setParameter("av","Available");
        List<Room> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getCount(Session session) throws Exception{
        Query query = session.createQuery("select distinct count(r.id) as roomCount,r.roomType.id as roomType from Room r " +
                "where r.availability='available' group by r.roomType.id ",Object[].class);
        List<Object[]> list = query.list();

        System.out.println(list);
        //ArrayList list1 = new ArrayList();
        return list;
    }

}
