package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
//    private final Configuration configuration = new Configuration().addAnnotatedClass(User.class);
//    private final SessionFactory sessionFactory = configuration.buildSessionFactory();
    private final SessionFactory sessionFactory = new Util().getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(100) NOT NULL, " +
                    "age INT NOT NULL);");
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS Users;");
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.save(new User(name, lastName, age));

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.remove(session.get(User.class, id));

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> mvpClub = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("SELECT * FROM Users;").addEntity(User.class);
            mvpClub = query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return mvpClub;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("TRUNCATE TABLE Users;");
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
