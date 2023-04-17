package lk.ijse.hostelmanagementsystem.service;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends SuperDTO,ID extends Serializable>{
    T search(ID id) ;
    boolean delete(T t);
    boolean update(T t);
    T save(T t);
    List<T> getAll();
}
