package lk.ijse.hostelmanagementsystem.util;

import lk.ijse.hostelmanagementsystem.dto.custom.*;
import lk.ijse.hostelmanagementsystem.entity.custom.*;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Converter {
    private static Converter converter;
    private Converter(){}
    public static Converter getInstance(){
        if(converter==null)converter=new Converter();
        return converter;
    }

    @SuppressWarnings("Individual Converter From StudentDTO to Student Entity")
    public Student toOnlyStudent(StudentDTO student){
        return new Student(student.getId(),student.getName(),student.getAddress(),
                student.getCity(),student.getContact(),student.getGmail(),null);
    }

    @SuppressWarnings("Individual Converter From Student Entity to StudentDTO")
    public StudentDTO toOnlyStudentDTO(Student student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),
                student.getCity(),student.getContact(),student.getGmail(),null);
    }

    @SuppressWarnings("Individual Converter From RoomDTO to Room Entity")
    public Room toOnlyRoom(RoomDTO room){
        return new Room(room.getId(),room.getAvailability(),null,null);
    }

    @SuppressWarnings("Individual Converter From Room Entity to Room DTO")
    public RoomDTO toOnlyRoomDTO(Room room){
        return new RoomDTO(room.getId(),room.getAvailability(),null,null);
    }

    @SuppressWarnings("Individual Converter From RoomType Entity to RoomTypeDTO")
    public RoomType toOnlyRoomType(RoomTypeDTO roomType){
        return new RoomType(roomType.getId(),roomType.getDescription(),roomType.getPrice(),
                null);
    }

    @SuppressWarnings("Individual Converter From RoomTypeDTO to RoomType Entity")
    public RoomTypeDTO toOnlyRoomTypeDTO(RoomType roomType){
        return new RoomTypeDTO(roomType.getId(),roomType.getDescription(),roomType.getPrice(),
                null);
    }

    @SuppressWarnings("Individual Converter From StudentRoomDTO to StudentRoom Entity")
    public StudentRoom toOnlyStudentRoom(StudentRoomDTO studentRoom){
        return new StudentRoom(studentRoom.getId(),studentRoom.getAdvance(),
                Date.valueOf(studentRoom.getPaymentDate()),Date.valueOf(studentRoom.getFrom())
                ,Date.valueOf(studentRoom.getTo()),null,null);
    }

    @SuppressWarnings("Individual Converter From StudentRoom Entity to StudentRoomDTO")
    public StudentRoomDTO toOnlyStudentRoomDTO(StudentRoom studentRoom){
        return new StudentRoomDTO(studentRoom.getId(),studentRoom.getAdvance(),
                studentRoom.getPaymentDate().toLocalDate(),studentRoom.getFromDate().toLocalDate()
                ,studentRoom.getToDate().toLocalDate(),null,null);
    }

    public User toOnlyUser(UserDTO user){
        return new User(user.getUserName(),user.getPassword());
    }

    public UserDTO toOnlyUser(User user){
        return new UserDTO(user.getUserName(),user.getPassword());
    }

    public List<RoomTM> getRoomDataToTable(List<Room> rooms){
        ArrayList<RoomTM> list = new ArrayList<>();
        for (Room ob:rooms){
            RoomTM roomTM = new RoomTM(ob.getId(), ob.getRoomType().getId(),
                    ob.getRoomType().getPrice(), toOnlyRoomDTO(ob),
                    toOnlyRoomTypeDTO(ob.getRoomType()));

            list.add(roomTM);
        }
        return list;
    }

    public List<ReservedOrAvailableRoomTM> convertToReserveRoomTM(List<StudentRoom> studentRoomList){
        ArrayList<ReservedOrAvailableRoomTM> list = new ArrayList<>();
        for(StudentRoom obj:studentRoomList){
            StudentRoomDTO studentRoomDTO = toOnlyStudentRoomDTO(obj);
            StudentDTO studentDTO = toOnlyStudentDTO(obj.getStudent());
            studentRoomDTO.setStudentDTO(studentDTO);
            list.add(new ReservedOrAvailableRoomTM(obj.getRoom().getId(),obj.getRoom().getRoomType().getDescription()
                    ,obj.getRoom().getRoomType().getId(),studentRoomDTO));
        }
        return list;
    }

    public List<RemainingKeyMoneyTM> getRemainingKeyMoneyList(List<StudentRoom> studentRoomList){
        ArrayList<RemainingKeyMoneyTM> list = new ArrayList<>();
        for(StudentRoom ob : studentRoomList){
            Student student = ob.getStudent();
            StudentDTO studentDTO = toOnlyStudentDTO(student);
            StudentRoomDTO studentRoomDTO = toOnlyStudentRoomDTO(ob);
            studentRoomDTO.setStudentDTO(studentDTO);
            RemainingKeyMoneyTM remaining = new RemainingKeyMoneyTM(ob.getRoom().getId(),
                    ob.getRoom().getRoomType().getId(), student.getName(),
                    ob.getRoom().getRoomType().getPrice(), ob.getAdvance(), studentRoomDTO);
            list.add(remaining);
        }
        return list;
    }

    public List<ReservedOrAvailableRoomTM> getAvailableRoomList(List<Room> rooms){
        ArrayList<ReservedOrAvailableRoomTM> list = new ArrayList<>();
        for(Room room: rooms){
            list.add(new ReservedOrAvailableRoomTM(room.getId(),room.getRoomType().getDescription()
                    ,room.getRoomType().getId(),null));
        }
        return list;
    }

    public HashMap<String,Integer> convertObjectArrayToStringIntegerHashMap(List<Object[]> all){
        HashMap<String,Integer> list = new HashMap<>();
        for (Object[] values:all ) {
            list.put(values[1].toString(),Integer.parseInt(values[0].toString()));
        }
        return list;
    }

    public HashMap<String,Double> convertObjectArrayToStringDoubleHashMap(List<Object[]> all){
        HashMap<String,Double> list = new HashMap<>();
        for (Object[] values:all ) {
            list.put(values[0].toString(),Double.parseDouble(values[1].toString()));
        }
        return list;
    }

}
