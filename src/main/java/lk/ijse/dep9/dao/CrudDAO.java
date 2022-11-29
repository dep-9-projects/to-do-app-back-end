package lk.ijse.dep9.dao;

import lk.ijse.dep9.entity.SuperEntity;
import lk.ijse.dep9.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    long count();
    void deleteById(ID pk);
    boolean existById(ID pk);
    List<T> findAll();
    Optional<T> findById(ID pk);
    T save(T entity);
    T update(T  entity);
}
