package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.RepoFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.RepoTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentRepo studentRepo;
    Converter converter;
    FactoryConfiguration factory;

    public StudentServiceImpl() {
        factory = FactoryConfiguration.getInstance();
        studentRepo = RepoFactory.getInstance().getRepo(RepoTypes.STUDENT);;
        converter = Converter.getInstance();
    }

    @Override
    public StudentDTO search(String s) {
        Session session = factory.getSession();
        try {
            Student search = studentRepo.search(s, session);
            return converter.toOnlyStudentDTO(search);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(StudentDTO studentDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = converter.toOnlyStudent(studentDTO);
            studentRepo.delete(student, session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = converter.toOnlyStudent(studentDTO);
            studentRepo.update(student, session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = converter.toOnlyStudent(studentDTO);
            Student save = studentRepo.save(student, session);
            transaction.commit();
            return converter.toOnlyStudentDTO(save);
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<StudentDTO> getAll() {
        Session session = factory.getSession();
        List<StudentDTO> list = new ArrayList<>();
        try {
            List<Student> all = studentRepo.getAll(session);
            for (Student student : all) {
                list.add(converter.toOnlyStudentDTO(student));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
