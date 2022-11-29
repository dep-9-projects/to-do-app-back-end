package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    long count();
    void deleteById(String userName);
    boolean existById(String userName);
    List<User> findAll();
    Optional<User> findById(String userName);
    User save(User user);
    User update(User user);


}
