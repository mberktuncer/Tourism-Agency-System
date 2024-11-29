package main.service;

import main.helper.Config;
import main.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserService {

    public static ArrayList<User> listAll(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        User user;
        try(Connection ignored = Config.connect()){
            Statement statement = Config.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword("password");
                user.setRole("role");
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(int id){
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword("password");
                user.setRole("role");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;

    }

    public static boolean add(String name, String surname, String userName, String password, String role){
        String query = "INSERT INTO users (name, surname, username, password, role) VALUES (?, ?, ?, ?, ?)";
        return true;
    }
}
