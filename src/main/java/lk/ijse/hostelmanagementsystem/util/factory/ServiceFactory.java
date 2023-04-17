package lk.ijse.hostelmanagementsystem.util.factory;

import lk.ijse.hostelmanagementsystem.service.custom.*;
import lk.ijse.hostelmanagementsystem.service.custom.impl.*;
import lk.ijse.hostelmanagementsystem.util.factory.types.ServiceType;

public class ServiceFactory {
    private final JoinService joinService;
    private final RoomService roomService;
    private final RoomTypeService roomTypeService;
    private final StudentRoomService studentRoomService;
    private final StudentService studentService;
    private final TransactionalService transactionalService;
    private final UserService userService;

    private static ServiceFactory serviceFactory;

    private ServiceFactory(){
        joinService=new JoinServiceImpl();
        roomService=new RoomServiceImpl();
        roomTypeService=new RoomTypeServiceImpl();
        studentRoomService=new StudentRoomServiceImpl();
        studentService=new StudentServiceImpl();
        transactionalService=new TransactionalServiceImpl();
        userService=new UserServiceImpl();
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null ? serviceFactory=new ServiceFactory():serviceFactory;
    }

    public <T>T getService(ServiceType type){
        switch (type){
            case JOIN:return (T)joinService;
            case ROOM:return (T)roomService;
            case ROOM_TYPE:return (T)roomTypeService;
            case STUDENT_ROOM:return (T)studentRoomService;
            case STUDENT:return (T)studentService;
            case USER:return (T)userService;
            case TRANSACTIONAL:return (T)transactionalService;
        }
        return null;
    }
}
