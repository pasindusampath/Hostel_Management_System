package lk.ijse.hostelmanagementsystem.repo.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRoomRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRoomRepoImpl implements StudentRoomRepo {
    @Override
    public StudentRoom search(String s, Session session) throws Exception {
        return session.get(StudentRoom.class,s);
    }

    @Override
    public void delete(StudentRoom studentRoom, Session session) throws Exception {
        session.delete(studentRoom);
    }

    @Override
    public void update(StudentRoom studentRoom, Session session) throws Exception {
        session.update(studentRoom);
    }

    @Override
    public StudentRoom save(StudentRoom studentRoom, Session session) throws Exception {
        session.save(studentRoom);
        return studentRoom;
    }

    @Override
    public List<StudentRoom> getAll(Session session) throws Exception {
        return session.createCriteria(StudentRoom.class).list();
    }

    @Override
    public List<StudentRoom> getReservedRoomList(Session session) throws Exception{
        Query query = session.createQuery
                ("from StudentRoom sr where sr.toDate >= :today and sr.room.availability=:av");
        LocalDate now = LocalDate.now();
        Date from = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        query.setParameter("today", from);
        query.setParameter("av","Not Available");//
        List<StudentRoom> list = query.list();
        return list;
    }

    @Override
    public List<StudentRoom> getRemainingKeyMoneyList(Session session) throws Exception{
        Query query = session.createQuery
                ("from StudentRoom sr where sr.toDate >= :today and sr.advance<sr.room.roomType.price");
        LocalDate now = LocalDate.now();
        Date from = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        query.setParameter("today", from);
        List<StudentRoom> list = query.list();
        return list;
    }

    public boolean updateKeyMoney(Session session,StudentRoom studentRoom) throws Exception{
        Query query = session.createQuery(
                "update StudentRoom sr set sr.advance=:amount where sr.id = :id");
        query.setParameter("amount",studentRoom.getAdvance());
        query.setParameter("id",studentRoom.getId());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean updateToDate(Session session, StudentRoom studentRoom) throws Exception {
        Query query = session.createQuery(
                "update StudentRoom sr set sr.toDate=:toDate where sr.id = :id");
        query.setParameter("toDate",studentRoom.getToDate());
        query.setParameter("id",studentRoom.getId());
        return query.executeUpdate()>0;
    }

    @Override
    public List<Object[]> getIncome(Session session) throws Exception{
        Query query = session.createQuery("select month(sr.paymentDate) as mon, " +
                "sum(sr.advance) as price from StudentRoom sr " +
                " where year(sr.paymentDate) = 2023 group by month(sr.paymentDate) ",Object[].class);
        List<Object[]> list = query.list();

        return list;
    }


}
