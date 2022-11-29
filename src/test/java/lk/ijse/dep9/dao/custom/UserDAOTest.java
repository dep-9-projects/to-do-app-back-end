package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.entity.User;
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

class UserDAOTest {

    private UserDAO userDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        String dbScript = new String(getClass().getResourceAsStream("/db-script.sql").readAllBytes());
        connection.createStatement().execute(dbScript);
        userDAO = DAOFactory.getInstance().getDAO(connection, DAOTypes.USER);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void count() {
        long count = userDAO.count();
        assertEquals(5, count);
    }

    @Test
    void deleteById() {
        assertThrows(RuntimeException.class,()->userDAO.deleteById("Manelka"));
        userDAO.deleteById("Pradeep");
        assertEquals(userDAO.count(),4);

    }

    @Test
    void existById() {
        assertTrue(userDAO.existById("Manelka"));
        assertTrue(userDAO.existById("Visal"));
        assertFalse(userDAO.existById("Sumal"));
    }

    @Test
    void findAll() {
        List<User> users = userDAO.findAll();
        assertEquals(users.size(),5);
    }

    @Test
    void findById() {
        Optional<User> manelka = userDAO.findById("Manelka");
        assertEquals(manelka.get().getUserName(),"Manelka");
        assertEquals(manelka.get().getPassword(),"123456789");
        assertEquals(manelka.get().getFullName(),"Manelka Nishan");
    }

    @Test
    void save() {
        User user = userDAO.save(new User("Nimal", "82630275", "Nimal Balasuriya"));
        assertEquals(userDAO.count(),6);
        User nimal = userDAO.findById("Nimal").get();
        System.out.println(user);
        System.out.println(nimal);
        assertEquals(user.getFullName(),nimal.getFullName());
        assertEquals(user.getPassword(),nimal.getPassword());
        assertEquals(user.getUserName(),nimal.getUserName());
    }

    @Test
    void update() {
        User user = userDAO.save(new User("Nimal", "82630275", "Nimal Balasuriya"));
        User user1 =new User("Nimal", "82630275", "Kamal Balasuriya");
        User updated = userDAO.update(user1);
        assertEquals(updated.getUserName(),user1.getUserName());
        assertEquals(updated.getFullName(),"Kamal Balasuriya");
    }
}