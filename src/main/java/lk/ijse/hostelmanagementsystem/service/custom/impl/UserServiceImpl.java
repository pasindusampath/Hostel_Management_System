package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.User;
import lk.ijse.hostelmanagementsystem.repo.custom.UserRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.UserRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepo repo = new UserRepoImpl();
    private Converter converter = Converter.getInstance();
    private FactoryConfiguration factory = FactoryConfiguration.getInstance();
    @Override
    public UserDTO search(String s) {
        Session session = factory.getSession();
        try {
            User search = repo.search(s, session);
            return converter.toOnlyUser(search);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        User user = converter.toOnlyUser(userDTO);
        try {
            repo.update(user,session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getAll(){
        return null;
    }
}
