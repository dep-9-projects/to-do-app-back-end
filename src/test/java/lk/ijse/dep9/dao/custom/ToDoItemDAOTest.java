package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.entity.ToDoItem;
import lk.ijse.dep9.entity.util.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ToDoItemDAOTest {

    private Connection connection;
    private ToDoItemDAO toDoItemDAO;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        String dbScript = new String(getClass().getResourceAsStream("/db-script.sql").readAllBytes());
        connection.createStatement().execute(dbScript);
        toDoItemDAO = DAOFactory.getInstance().getDAO(connection, DAOTypes.TO_DO_ITEM);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void count() {
        long count = toDoItemDAO.count();
        assertEquals(2,count);
    }

    @Test
    void deleteById() {
        toDoItemDAO.deleteById(1);
        assertEquals(1,toDoItemDAO.count());
        toDoItemDAO.deleteById(2);
        assertEquals(0,toDoItemDAO.count());
    }

    @Test
    void existById() {
        boolean bool1 = toDoItemDAO.existById(1);
        boolean bool2 = toDoItemDAO.existById(10);
        assertTrue(bool1);
        assertFalse(bool2);
    }

    @Test
    void findAll() {
        List<ToDoItem> items = toDoItemDAO.findAll();
        assertEquals(2,items.size());
    }

    @Test
    void findById() {
        Optional<ToDoItem> toDoItem = toDoItemDAO.findById(1);
        assertTrue(toDoItem.isPresent());
    }

    @Test
    void save() {
        toDoItemDAO.save( new ToDoItem("Manelka","Go Home",Status.DONE));
        assertEquals(3,toDoItemDAO.count());
    }

    @Test
    void update() {
        ToDoItem toDoItem = toDoItemDAO.save(new ToDoItem("Manelka", "Go Home", Status.NOT_DONE));
        ToDoItem toDoItem1 = new ToDoItem(toDoItem.getId(),"Manelka", "Go Home Visal", Status.DONE);
        ToDoItem updatedTodo = toDoItemDAO.update(toDoItem1);
        assertEquals(toDoItem1.getStatus(),updatedTodo.getStatus());
        assertEquals(toDoItem1.getDescription(),updatedTodo.getDescription());
        assertEquals(toDoItem1.getUsername(),updatedTodo.getUsername());

    }

    @Test
    void deleteAll() {
        toDoItemDAO.save( new ToDoItem("Manelka","Go Home",Status.DONE));
        toDoItemDAO.deleteAll();
        assertEquals(0,toDoItemDAO.count());
        toDoItemDAO.save( new ToDoItem("Naveen","Go Home Visal",Status.DONE));
        assertEquals(1,toDoItemDAO.count());
    }
}