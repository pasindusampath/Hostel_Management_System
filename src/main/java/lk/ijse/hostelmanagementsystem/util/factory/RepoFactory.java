package lk.ijse.hostelmanagementsystem.util.factory;

import lk.ijse.hostelmanagementsystem.repo.SuperRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.*;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.*;
import lk.ijse.hostelmanagementsystem.util.factory.types.RepoTypes;

public class RepoFactory {
    private static RepoFactory repoFactory;
    private final RoomRepo roomRepo;
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final RoomTypeRepo roomTypeRepo;
    private final StudentRoomRepo studentRoomRepo;

    private RepoFactory(){
        roomRepo=new RoomRepoImpl();
        userRepo=new UserRepoImpl();
        studentRepo=new StudentRepoImpl();
        roomTypeRepo = new RoomTypeRepoImpl();
        studentRoomRepo=new StudentRoomRepoImpl();
    }


    public <T extends SuperRepo>T getRepo(RepoTypes repoTypes){
        switch (repoTypes){
            case ROOM:return (T) roomRepo;
            case USER:return (T) userRepo;
            case STUDENT:return (T)studentRepo;
            case ROOM_TYPE:return (T)roomTypeRepo;
            case STUDENT_ROOM:return (T)studentRoomRepo;
        }
        return null;
    }

    public static RepoFactory getInstance(){
        return repoFactory==null ? repoFactory=new RepoFactory() : repoFactory;
    }

}
