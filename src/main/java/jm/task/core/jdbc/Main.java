package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoHibernateImpl UDHI = new UserDaoHibernateImpl();
        UDHI.createUsersTable();
        UDHI.saveUser("Egor", "Oleynikov", (byte) 20);
        UDHI.saveUser("Sergey", "Vlasov", (byte) 20);
        UDHI.saveUser("Vadim", "Kireev", (byte) 19);
        UDHI.saveUser("Alex", "Arifulin", (byte) 24);
        System.out.println(UDHI.getAllUsers());
        UDHI.cleanUsersTable();
        UDHI.dropUsersTable();


//        UserServiceImpl USI = new UserServiceImpl();
//        USI.createUsersTable();
//        USI.saveUser("Egor", "Oleynikov", (byte) 20);
//        USI.saveUser("Sergey", "Vlasov", (byte) 20);
//        USI.saveUser("Vadim", "Kireev", (byte) 19);
//        USI.saveUser("Alex", "Arifulin", (byte) 24);
//        System.out.println(USI.getAllUsers());
//        USI.cleanUsersTable();
//        USI.dropUsersTable();
    }
}
