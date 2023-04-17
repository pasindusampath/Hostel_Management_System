package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
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
        studentRepo = new StudentRepoImpl();
        converter = Converter.getInstance();
    }

    @Override
    public StudentDTO search(String s) {
        /*Student search = studentRepo.search(s, session);
        //Student -> StudentDTO
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(search.getId());
        studentDTO.setName(search.getName());
        studentDTO.setAddress(search.getAddress());
        studentDTO.setCity(search.getCity());
        studentDTO.setContact(search.getContact());
        studentDTO.setGmail(search.getGmail());
        List<StudentRoom> studentRoomList = search.getStudentRoomList();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        List<StudentRoomDTO> list = new ArrayList<>();
        for (StudentRoom ob : studentRoomList) {
            RoomDTO obb = new RoomDTO();
            Room room = ob.getRoom();
            obb.setId(room.getId());
            obb.setAvailability(room.getAvailability());
            obb.setStudentRoomList(list);
            obb.setRoomType(new RoomTypeDTO(
                                    room.getRoomType().getId(),
                                    room.getRoomType().getDescription(),
                                    room.getRoomType().getPrice(),
                                    roomDTOS

                    )
            );
        }


        for (StudentRoom ob : studentRoomList) {
            StudentRoomDTO obb = new StudentRoomDTO();
            obb.setStudent(studentDTO);
            //obb.setStudent();
        }

        //studentDTO.setStudentRoomList();*/
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
