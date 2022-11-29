package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.dao.custom.UserDAO;
import lk.ijse.dep9.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long count() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(`userName`) FROM `user`");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String userName) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE  FROM `user` WHERE `userName`=?");
            stm.setString(1,userName);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existById(String userName) {
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `user` WHERE `userName`=?");
            stm.setString(1,userName);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `user`");
            ResultSet rst = stm.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rst.next()){
                String userName = rst.getString("userName");
                String password = rst.getString("password");
                String fullName = rst.getString("fullName");

                users.add(new User(userName,password,fullName));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(String userName) {
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `user` WHERE `userName`=?");
            stm.setString(1,userName);
            ResultSet rst = stm.executeQuery();
            if (rst.next()){
                String password = rst.getString("password");
                String fullName = rst.getString("fullName");

                return Optional.of(new User(userName, password, fullName));
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User save(User user) {
        try{
            PreparedStatement stm = connection.prepareStatement("INSERT INTO `user` (`userName`, `password`, `fullName`) VALUES (?,?,?)");
            stm.setString(1,user.getUserName());
            stm.setString(2,user.getPassword());
            stm.setString(3,user.getFullName());

            if(stm.executeUpdate()==1){
                return user;
            }else {
                throw new RuntimeException("Failed to save the user");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User user){
        try{
            PreparedStatement stm = connection.prepareStatement("UPDATE `user` SET `password`=? ,`fullName`=? WHERE `userName`=?");
            stm.setString(1, user.getPassword());
            stm.setString(2, user.getFullName());
            stm.setString(3, user.getUserName());

            if (stm.executeUpdate()==1){
                return user;
            }else {
                throw new RuntimeException("Failed to update the user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
