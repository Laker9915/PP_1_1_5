package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl USI = new UserServiceImpl();
        USI.createUsersTable();
        USI.saveUser("Egor", "Oleynikov", (byte) 21);
        USI.saveUser("Sergey", "Vlasov", (byte) 20);
        USI.saveUser("Vadim", "Kireev", (byte) 19);
        USI.saveUser("Alex", "Arifulin", (byte) 24);
        USI.getAllUsers();
        USI.cleanUsersTable();
        USI.dropUsersTable();
    }
}
