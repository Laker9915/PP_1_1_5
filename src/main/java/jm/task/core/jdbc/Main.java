package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl USI = new UserServiceImpl();
        USI.createUsersTable();
        USI.saveUser("Egor", "Oleynikov", (byte) 21);
        USI.saveUser("Sergey", "Vlasov", (byte) 20);
        USI.saveUser("Vadim", "Kireev", (byte) 19);
        USI.saveUser("Alex", "Arifulin", (byte) 24);
        System.out.println(USI.getAllUsers());
        USI.cleanUsersTable();
        USI.dropUsersTable();
    }
}
