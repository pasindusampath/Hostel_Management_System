package lk.ijse.hostelmanagementsystem.repo.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.User;
import lk.ijse.hostelmanagementsystem.repo.custom.UserRepo;
import org.hibernate.Session;

import java.util.List;

public class UserRepoImpl implements UserRepo {
    @Override
    public User search(String s, Session session) throws Exception {
        return session.get(User.class,s);
    }

    @Override
    public void delete(User user, Session session) throws Exception {

    }

    @Override
    public void update(User user, Session session) throws Exception {
        session.update(user);
    }

    @Override
    public User save(User user, Session session) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll(Session session) throws Exception {
        return null;
    }
}
