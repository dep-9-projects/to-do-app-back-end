package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.CrudDAO;
import lk.ijse.dep9.entity.ToDoItem;

public interface ToDoItemDAO extends CrudDAO<ToDoItem,Integer> {
    void deleteAll();

}
