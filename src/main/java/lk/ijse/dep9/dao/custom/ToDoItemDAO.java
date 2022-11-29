package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.entity.ToDoItem;
import lk.ijse.dep9.entity.util.Status;

import java.util.List;
import java.util.Optional;

public interface ToDoItemDAO {
    long count();
    void deleteById(int id);
    boolean existsById(int id);
    List<ToDoItem> findAll();
    Optional<ToDoItem> findById(int id);
    ToDoItem save(ToDoItem toDoItem);
    ToDoItem update(ToDoItem toDoItem);
}
