package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.dao.custom.ToDoItemDAO;
import lk.ijse.dep9.entity.ToDoItem;
import lk.ijse.dep9.entity.util.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoItemDAOImpl implements ToDoItemDAO {

    private final Connection connection;

    public ToDoItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long count() {
        try (PreparedStatement stm = connection.prepareStatement("SELECT COUNT(id) FROM `to-do-item`")) {
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM `to-do-item` WHERE id=?")) {
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(int id) {
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM `to-do-item` WHERE id=?")) {
            stm.setInt(1,id);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ToDoItem> findAll() {
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM `to-do-item`")) {
            ResultSet rst = stm.executeQuery();
            ArrayList<ToDoItem> toDoItems = new ArrayList<>();
            while (rst.next()){
                int id = rst.getInt("id");
                String username = rst.getString("username");
                String description = rst.getString("description");
                Status status = Status.valueOf(rst.getString("status"));
                toDoItems.add(new ToDoItem(id,username,description,status));
            }
            return toDoItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<ToDoItem> findById(int id) {
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM `to-do-item` WHERE id=?")) {
            stm.setInt(1,id);
            ResultSet rst = stm.executeQuery();
            if (rst.next()){
                String username = rst.getString("username");
                String description = rst.getString("description");
                Status status = Status.valueOf(rst.getString("status"));
                return Optional.of(new ToDoItem(id,username,description,status));
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ToDoItem save(ToDoItem toDoItem) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO `to-do-item` (username, description, status) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1,toDoItem.getUsername());
            stm.setString(2,toDoItem.getDescription());
            stm.setString(3,toDoItem.getStatus().toString());
            ResultSet rst = stm.getGeneratedKeys();
            rst.next();
            int id = rst.getInt(1);
            toDoItem.setId(id);

            if (stm.executeUpdate() == 1){
                return toDoItem;
            }else {
                throw new SQLException("Failed to save the toDoItem");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ToDoItem update(ToDoItem toDoItem) {
        return null;
    }
}
