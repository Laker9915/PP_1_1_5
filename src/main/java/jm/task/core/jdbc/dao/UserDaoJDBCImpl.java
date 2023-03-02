package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util connectionManager = new Util();
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Connection connection = connectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(50) NOT NULL, " +
                            "lastName VARCHAR(100) NOT NULL, " +
                            "age INT NOT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = connectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = connectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(
                "INSERT users(name, lastName, age) VALUES (?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(String.format("User с именем – %s добавлен в базу данных;", name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = connectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?;")){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> mvpClub= new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users;");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                mvpClub.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mvpClub;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = connectionManager.getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE TABLE users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
