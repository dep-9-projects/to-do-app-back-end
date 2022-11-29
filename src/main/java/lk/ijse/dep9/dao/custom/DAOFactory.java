package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.SuperDAO;
import lk.ijse.dep9.dao.custom.impl.DAOTypes;
import lk.ijse.dep9.dao.custom.impl.ToDoItemDAOImpl;
import lk.ijse.dep9.dao.custom.impl.UserDAOImpl;

import java.lang.reflect.Member;
import java.sql.Connection;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getInstance(){
        return (daoFactory==null)? daoFactory=new DAOFactory():daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return (T) new UserDAOImpl(connection);

            case TO_DO_ITEM:
                return (T) new ToDoItemDAOImpl(connection);

            default:
                return null;
        }
    }
}
