package lk.ijse.hostelmanagementsystem.repo.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRepo;
import org.hibernate.Session;

import java.util.List;

public class StudentRepoImpl implements StudentRepo{
    @Override
    public Student search(String s, Session session) throws Exception {
        return session.get(Student.class,s);
    }

    @Override
    public void delete(Student student, Session session) throws Exception {
        session.delete(student);
    }

    @Override
    public void update(Student student, Session session) throws Exception {
        session.update(student);
    }

    @Override
    public Student save(Student student, Session session) throws Exception {
        String id = (String)session.save(student);
        student.setId(id);
        return student;
    }

    @Override
    public List<Student> getAll(Session session) throws Exception {
        return session.createCriteria(Student.class).list();
    }
}
