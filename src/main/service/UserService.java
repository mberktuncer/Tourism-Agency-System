package main.service;

import main.helper.Config;
import main.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserService {

    public static ArrayList<User> listAll(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        User user;
        try(Connection connection = Config.connect()){
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


}
